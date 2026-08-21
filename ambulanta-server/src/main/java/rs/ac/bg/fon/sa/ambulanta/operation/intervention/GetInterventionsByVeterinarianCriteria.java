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
 * vezanog za veterinara.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Ukoliko prosleđeni kriterijum (String) ima nastavak "Lastname", pretraga
 * se vrši po prezimenu veterinara (delimično poklapanje, počevši od zadatog
 * niza karaktera). U suprotnom, pretraga se vrši po identifikatoru veterinara.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetInterventionsByVeterinarianCriteria extends AbstractSO{
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
     * prezimenu veterinara (ako kriterijum ima nastavak "Lastname") ili po
     * identifikatoru veterinara (u suprotnom), i čuva rezultat pretrage kao
     * rezultat operacije.
     *
     * @param param Kriterijum pretrage kao String.
     * @throws Exception Ako pretraga intervencija u bazi podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        String criteria = (String) param;
        
        if(criteria.endsWith("Lastname")){
            String[] s = criteria.split("Lastname");
            criteria = s[0].toString();
            String whereSection = "WHERE v.lastname LIKE '"+criteria+"%'";
            interventions = repository.getByCriteria(new Intervention(), whereSection); 
        }
        else{
            String whereSection = "WHERE i.idVeterinarian="+criteria;
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
