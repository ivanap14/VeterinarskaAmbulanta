/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za preuzimanje jedne intervencije iz baze
 * podataka na osnovu njenog identifikatora, zajedno sa pripadajućim stavkama
 * (uslugama).
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetIntervention extends AbstractSO{
	/**
     * Intervencija preuzeta nakon uspešnog izvršavanja operacije, zajedno sa
     * pripadajućim stavkama.
     */
    private Intervention intervention;
    
    /**
     * Ne vrši eksplicitnu proveru preduslova; parametar se koristi direktno
     * prilikom izvršavanja operacije.
     *
     * @param param Intervencija čiji se identifikator koristi za pretragu.
     * @throws Exception Nikada se ne baca.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Preuzima intervenciju iz baze podataka na osnovu identifikatora
     * prosleđene intervencije, a zatim učitava i pripadajuće stavke
     * intervencije na osnovu njenog identifikatora.
     *
     * @param param Intervencija čiji se identifikator koristi za pretragu.
     * @throws Exception Ako preuzimanje intervencije ili njenih stavki iz
     * baze podataka ne uspe, ili ako intervencija sa prosleđenim
     * identifikatorom ne postoji.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {

        Intervention intervention1 = (Intervention) param;
        
        List<Intervention> interventions = repository.getByCriteria(param, "WHERE i.id="+intervention1.getId());
        
        intervention = interventions.get(0);
        
        List<InterventionItem> items = repository.getByCriteria(new InterventionItem(), "WHERE idIntervention="+intervention.getId());
        intervention.setInterventionItems(items);
        
    }

    /**
     * Vraća intervenciju preuzetu nakon izvršavanja operacije, zajedno sa
     * njenim stavkama.
     *
     * @return pronađena intervencija sa učitanim stavkama
     */
    public Intervention getIntervention() {
        return intervention;
    }
    
}
