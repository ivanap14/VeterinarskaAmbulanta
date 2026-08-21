/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za pretragu intervencija na osnovu kriterijuma
 * koji se odnosi na samu intervenciju (datum, iznos ili identifikator).
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * U zavisnosti od nastavka prosleđenog kriterijuma,
 * pretraga se vrši po datumu intervencije, po iznosu većem od zadate
 * vrednosti, po iznosu manjem od zadate vrednosti, a u suprotnom po
 * identifikatoru intervencije.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetInterventionsByInterventionCriteria extends AbstractSO{
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
     * datumu, po iznosu (veći ili manji od zadate vrednosti) ili po
     * identifikatoru intervencije, i čuva rezultat pretrage kao rezultat
     * operacije.
     *
     * @param param Kriterijum pretrage kao String.
     * @throws Exception Ako pretraga intervencija u bazi podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        String criteria = (String) param;
        
        if(criteria.endsWith("Date")){
            String[] s = criteria.split("Date");
            criteria = s[0].toString();
            String whereSection = "WHERE i.date='"+criteria+"'";
            interventions = repository.getByCriteria(new Intervention(), whereSection); 
        }
        else if(criteria.endsWith(">")){
            String[] s = criteria.split(">");
            criteria = s[0].toString();
            String whereSection = "WHERE i.totalAmountWithDiscount>"+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
        else if(criteria.endsWith("<")){
            String[] s = criteria.split("<");
            criteria = s[0].toString();
            String whereSection = "WHERE i.totalAmountWithDiscount<"+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
        else{
            String whereSection = "WHERE i.id="+criteria;
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
