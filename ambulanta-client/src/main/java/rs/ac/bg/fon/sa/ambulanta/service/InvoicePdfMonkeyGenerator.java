package rs.ac.bg.fon.sa.ambulanta.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.ac.bg.fon.sa.ambulanta.domain.*;

/**
 * Klasa zadužena za generisanje PDF računa za intervenciju putem
 * eksternog PDFMonkey servisa.
 *
 * Podaci o intervenciji (veterinar, vlasnik, životinja, stavke usluga,
 * popusti i ukupni iznosi) se serijalizuju u JSON i šalju PDFMonkey API-ju
 * radi generisanja dokumenta na osnovu unapred definisanog šablona.
 * Generisanje dokumenta na strani PDFMonkey servera je asinhrono, pa se
 * prvo pokreće generisanje startGenerating(Intervention), a
 * zatim se, nakon kraćeg čekanja, proverava status i preuzima link za
 * preuzimanje gotovog dokumenta getUrl(String).
 *
 * @author Korisnik
 */
public class InvoicePdfMonkeyGenerator {
		/**
     	* API ključ za autentifikaciju na PDFMonkey servisu.
     	*/
		private static final String API_KEY = "_7XmdsBcaJGUFcuJmTxM";
		/**
         * Identifikator PDFMonkey šablona koji se koristi za generisanje
         * računa.
         */
		private static final String TEMPLATE_ID = "7F8A07CD-43CF-4710-B081-71A27B48C8A1";
		/**
         * URL PDFMonkey API-ja za rad sa dokumentima.
         */
		private static final String API_URL = "https://api.pdfmonkey.io/api/v1/documents";

		/**
         * HTTP klijent koji se koristi za slanje zahteva ka PDFMonkey API-ju.
         */
	    private final HttpClient client = HttpClient.newHttpClient();
	    
	    /**
         * Gson instanca koja se koristi za (de)serijalizaciju JSON podataka.
         */
	    private final Gson gson = new Gson();

	    /**
         * Kreira novi dokument (račun) na PDFMonkey serveru na osnovu
         * podataka prosleđene intervencije i identifikatora unapred
         * definisanog šablona.
         *
         * Podaci o intervenciji (broj računa, datum, veterinar, vlasnik,
         * životinja, stavke usluga sa cenama i količinama, popusti i
         * ukupni iznosi, napomena) se pakuju u JSON payload i šalju kao
         * POST zahtev PDFMonkey API-ju. Generisanje dokumenta na strani
         * servera traje izvesno vreme (par sekundi), pa nakon poziva ove
         * metode dokument još uvek ne mora biti spreman za preuzimanje —
         * za to se koristi metoda getUrl(String).
         *
         * @param intervention Intervencija na osnovu koje se generiše
         * PDF račun. Ne sme biti null. Potrebno je da poseduje popunjene
         * podatke o veterinaru, životinji, vlasniku i stavkama intervencije.
         * @return identifikator dokumenta kreiranog na PDFMonkey serveru,
         * koji se koristi za kasniju proveru statusa i preuzimanje dokumenta
         * @throws Exception Ako slanje zahteva ka PDFMonkey API-ju ne uspe,
         * ili ako server ne vrati status 201 (kreiran) kao odgovor.
         */
	    public String startGenerating(Intervention intervention) throws Exception {
	        JsonObject payload = new JsonObject();
	        payload.addProperty("brojRacuna", intervention.getId());
	        payload.addProperty("datum", intervention.getDate().toString());
	        payload.addProperty("veterinar", intervention.getVeterinarian().getFirstname() + " "
	                + intervention.getVeterinarian().getLastname());
	        payload.addProperty("vlasnikImePrezime", intervention.getAnimal().getOwner().getFirstname() + " "
	                + intervention.getAnimal().getOwner().getLastname());
	        payload.addProperty("vlasnikJmbg", intervention.getAnimal().getOwner().getJmbg());
	        payload.addProperty("vlasnikTelefon", intervention.getAnimal().getOwner().getPhone());
	        payload.addProperty("vlasnikAdresa", intervention.getAnimal().getOwner().getAddress());
	        payload.addProperty("zivotinja", intervention.getAnimal().getName() 
	        		+ " ("+ intervention.getAnimal().getSpecies() + ")");

	        JsonArray usluge = new JsonArray();
	        if (intervention.getInterventionItems() != null) {
	            for (InterventionItem item : intervention.getInterventionItems()) {
	                JsonObject u = new JsonObject();
	                u.addProperty("rb", item.getRb());
	                u.addProperty("naziv", item.getService().getName());
	                u.addProperty("cena", item.getPrice());
	                u.addProperty("kolicina", item.getQuantity());
	                u.addProperty("iznos", item.getAmount());
	                usluge.add(u);
	            }
	        }
	        payload.add("usluge", usluge);

	        payload.addProperty("popustLojalnost", intervention.getDiscountForLoyalty());
	        payload.addProperty("popustBrojUsluga", intervention.getDiscountForNumberOfServices());
	        payload.addProperty("ukupnoBezPopusta", intervention.getTotalAmountWithoutDiscount());
	        payload.addProperty("ukupnoSaPopustom", intervention.getTotalAmountWithDiscount());
	        payload.addProperty("napomena", intervention.getNotes());

	        JsonObject document = new JsonObject();
	        document.addProperty("document_template_id", TEMPLATE_ID);
	        document.addProperty("status", "pending");
	        document.add("payload", payload);

	        JsonObject body = new JsonObject();
	        body.add("document", document);

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(API_URL))
	                .header("Authorization", "Bearer " + API_KEY)
	                .header("Content-Type", "application/json")
	                .timeout(Duration.ofSeconds(15))
	                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
	                .build();

	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        if (response.statusCode() == 201) {
	            JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);
	            return responseJson.getAsJsonObject("document").get("id").getAsString();
	        }
	        throw new RuntimeException("Greška pri generisanju PDF-a: " + response.body());
	    }

	    /**
         * Proverava trenutni status dokumenta sa prosleđenim identifikatorom
         * na PDFMonkey serveru i, ukoliko je generisanje uspešno završeno,
         * vraća link za njegovo preuzimanje.
         *
         * Poziva se nakon poziva metode startGenerating(Intervention)
         * i kratkog čekanja, s obzirom na to da generisanje dokumenta na serveru nije trenutno.
         *
         * @param documentId Identifikator dokumenta dobijen prilikom
         * pokretanja generisanja (rezultat metode startGenerating(Intervention)).
         * @return link za preuzimanje gotovog PDF dokumenta, ili null
         * ukoliko dokument još uvek nije uspešno generisan (status različit
         * od "success")
         * @throws Exception Ako slanje zahteva ka PDFMonkey API-ju ili
         * parsiranje odgovora ne uspe.
         */
	    public String getUrl(String documentId) throws Exception {
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(API_URL + "/" + documentId))
	                .header("Authorization", "Bearer " + API_KEY)
	                .GET()
	                .build();

	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
	        JsonObject doc = json.getAsJsonObject("document");

	        String status = doc.get("status").getAsString();
	        if (!"success".equals(status)) {
	            return null; 
	        }
	        return doc.get("download_url").getAsString();
	    }
}
