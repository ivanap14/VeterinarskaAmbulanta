/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za pretragu intervencija na osnovu kriterijuma
 * vezanog za životinju.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Ukoliko prosleđeni kriterijum (String) ima nastavak "Species", pretraga se
 * vrši po vrsti životinje (delimično poklapanje, počevši od zadatog niza
 * karaktera). U suprotnom, pretraga se vrši po identifikatoru životinje.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetInterventionsByAnimalCriteria extends AbstractSO{
	/**
     * Lista intervencija koje odgovaraju zadatom kriterijumu, popunjena
     * nakon uspešnog izvršavanja operacije.
     */
	private List<Intervention> interventions;
    
	/**
     * Ne vrši eksplicitnu proveru preduslova; format kriterijuma se
     * proverava prilikom izvršavanja operacije.
     *
     * @param param Kriterijum pretrage kao String.
     * @throws Exception Nikada se ne baca.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Na osnovu formata prosleđenog kriterijuma pretražuje intervencije po
     * vrsti životinje (ako kriterijum ima nastavak "Species") ili po
     * identifikatoru životinje (u suprotnom), i čuva rezultat pretrage kao
     * rezultat operacije.
     *
     * @param param Kriterijum pretrage kao String.
     * @throws Exception Ako pretraga intervencija u bazi podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        String criteria = (String) param;
        
        if(criteria.endsWith("Species")){
            String[] s = criteria.split("Species");
            criteria = s[0].toString();
            String whereSection = "WHERE a.species LIKE '"+criteria+"%'";
            interventions = repository.getByCriteria(new Intervention(), whereSection); 
        }
        else{
            String whereSection = "WHERE i.idAnimal="+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
    }

    /**
     * Vraća listu intervencija koje odgovaraju zadatom kriterijumu pretrage.
     * Ukoliko nijedna intervencija ne odgovara kriterijumu, vraća praznu listu.
     *
     * @return lista pronađenih intervencija, odnosno prazna lista ako
     * nijedna intervencija ne zadovoljava kriterijum pretrage
     */
    public List<Intervention> getInterventions() {
        return interventions;
    }
}
