/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za pretragu intervencija na osnovu naziva
 * usluge koju sadrže.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Prvo se pronalaze sve stavke intervencije čija usluga po nazivu odgovara
 * (delimično poklapanje) prosleđenom kriterijumu, a zatim se za svaku takvu
 * stavku učitava pripadajuća intervencija.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetInterventionsByServiceCriteria extends AbstractSO{
	/**
     * Lista intervencija koje sadrže uslugu čiji naziv odgovara zadatom
     * kriterijumu, popunjena nakon uspešnog izvršavanja operacije.
     */
	private List<Intervention> interventions;
    
	/**
     * Ne vrši eksplicitnu proveru preduslova; kriterijum se koristi direktno
     * prilikom izvršavanja operacije.
     *
     * @param param Kriterijum pretrage kao String.
     * @throws Exception Nikada se ne baca.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Pronalazi sve stavke intervencije čija usluga po nazivu sadrži
     * prosleđeni kriterijum, a zatim za svaku pronađenu stavku učitava
     * pripadajuću intervenciju i dodaje je u listu rezultata, izbegavajući
     * duplikate koji ne postoje u bazi podataka.
     *
     * @param param Kriterijum pretrage kao String.
     * @throws Exception Ako pretraga stavki intervencije ili intervencija u
     * bazi podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        interventions = new ArrayList<>();
        
        String criteria = (String) param;
        String whereSection = "WHERE s.name LIKE '%"+criteria+"%'";
        
        List<InterventionItem> items = repository.getByCriteria(new InterventionItem(), whereSection);
        
        for (InterventionItem item : items) {
            String whereSection1 = "WHERE i.id="+item.getIntervention().getId();
            List<Intervention> result = repository.getByCriteria(new Intervention(), whereSection1);

            if (!result.isEmpty()) {
                interventions.add(result.get(0));
            }
        }
        
    }

    /**
     * Vraća listu intervencija koje sadrže uslugu čiji naziv odgovara
     * zadatom kriterijumu pretrage. Ukoliko nijedna intervencija ne
     * odgovara kriterijumu, vraća praznu listu.
     *
     * @return lista pronađenih intervencija, odnosno prazna lista ako
     * nijedna intervencija ne sadrži uslugu koja zadovoljava kriterijum
     * pretrage
     */
    public List<Intervention> getInterventions() {
        return interventions;
    }
}
