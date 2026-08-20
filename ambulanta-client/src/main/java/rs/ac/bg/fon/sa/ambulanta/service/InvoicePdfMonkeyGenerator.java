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


public class InvoicePdfMonkeyGenerator {
	 private static final String API_KEY = "_7XmdsBcaJGUFcuJmTxM";
	    private static final String TEMPLATE_ID = "7F8A07CD-43CF-4710-B081-71A27B48C8A1";
	    private static final String API_URL = "https://api.pdfmonkey.io/api/v1/documents";

	    private final HttpClient client = HttpClient.newHttpClient();
	    private final Gson gson = new Gson();

	    /** Kreira dokument na PDFMonkey serveru i vraća njegov ID (generisanje traje par sekundi). */
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

	    /** Proverava status i vraća download_url kada je dokument gotov. Zove se posle kratkog čekanja. */
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
