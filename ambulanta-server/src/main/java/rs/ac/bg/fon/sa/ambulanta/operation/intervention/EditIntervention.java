/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za izmenu podataka o postojećoj intervenciji
 * u bazi podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Pre same izmene proverava da li intervencija sa prosleđenim identifikatorom
 * postoji u bazi podataka i da li poseduje bar jednu stavku (uslugu). Prilikom
 * izvršavanja operacije, postojeće stavke intervencije se brišu i zamenjuju
 * novoprosleđenim stavkama.
 *
 * @author Korisnik
 * @version 1.0
 */
public class EditIntervention extends AbstractSO{

	/**
     * Intervencija nad kojom se vrši izmena, odnosno intervencija sa
     * izmenjenim podacima nakon uspešnog izvršavanja operacije.
     */
    private Intervention intervention;
    
    /**
     * Proverava da li je prosleđen parametar, da li predstavlja instancu
     * klase Intervention, da li intervencija sa datim identifikatorom
     * postoji u bazi podataka i da li poseduje bar jednu stavku.
     *
     * @param param Intervencija sa izmenjenim podacima. Ne sme biti null.
     * @throws Exception Ako je parametar null, nije instanca klase Intervention,
     * intervencija sa prosleđenim identifikatorom ne postoji u bazi podataka
     * ili ne sadrži nijednu uslugu.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Intervention)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        intervention = (Intervention) param;

        checkExist(intervention);
         
        checkOtherConstraints(intervention);
    }

    /**
     * Briše postojeće stavke prosleđene intervencije, menja samu
     * intervenciju u bazi podataka, a zatim ponovo dodaje njene stavke
     * kako bi bile ažurne.
     *
     * @param param Intervencija sa izmenjenim podacima i stavkama.
     * @throws Exception Ako brisanje starih stavki, izmena intervencije ili
     * dodavanje novih stavki u bazu podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
         
        intervention = (Intervention) param;

        InterventionItem deleteItem = new InterventionItem();
        deleteItem.setIntervention(intervention);

        repository.delete(deleteItem);

        intervention = (Intervention) repository.edit(intervention);

        List<InterventionItem> items = intervention.getInterventionItems();

        for (InterventionItem item : items) {
            repository.add(item);
        }
    }

    /**
     * Vraća intervenciju sa izmenjenim podacima nakon izvršavanja operacije.
     *
     * @return izmenjena intervencija
     */
    public Intervention getIntervention() {
        return intervention;
    }
    
    /**
     * Proverava da li intervencija sa identifikatorom prosleđene intervencije
     * postoji u bazi podataka.
     *
     * @param intervention Intervencija čije se postojanje proverava.
     * @throws Exception Ako intervencija sa datim identifikatorom ne postoji
     * u bazi podataka.
     */
    private void checkExist(Intervention intervention) throws Exception {
        List<Intervention> list = repository.getByCriteria(new Intervention(),"WHERE i.id=" + intervention.getId());

        if(list.isEmpty()){
            throw new Exception("Intervencija ne postoji.");
        }
    }
    
    /**
     * Proverava da li prosleđena intervencija sadrži bar jednu stavku (uslugu).
     *
     * @param intervention1 Intervencija čije se stavke proveravaju. Nije
     * korišćen, provera se vrši nad poljem klase.
     * @throws Exception Ako intervencija ne sadrži nijednu uslugu.
     */
    private void checkOtherConstraints(Intervention intervention1) throws Exception {
        if (intervention.getInterventionItems().isEmpty()) {
            throw new Exception("Intervencija mora sadržati najmanje jednu uslugu.");
        }
    }

    
    
}
