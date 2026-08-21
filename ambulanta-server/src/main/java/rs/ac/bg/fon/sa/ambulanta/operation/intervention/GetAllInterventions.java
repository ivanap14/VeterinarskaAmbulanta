/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za preuzimanje svih intervencija iz baze podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Operacija ne zahteva ulazni parametar niti proveru preduslova.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetAllInterventions extends AbstractSO{
	/**
     * Lista svih intervencija preuzeta nakon uspešnog izvršavanja operacije.
     */
	private List<Intervention> interventions;

	/**
     * Ne vrši nikakvu proveru s obzirom na to da operacija ne zahteva
     * ulazni parametar.
     *
     * @param param Vrednsot null.
     * @throws Exception Nikada se ne baca.
     */
    @Override
    protected void preconditions(Object param) throws Exception {

    }

    /**
     * Preuzima sve intervencije iz baze podataka i čuva ih kao rezultat operacije.
     *
     * @param param Vrednsot null.
     * @throws Exception Ako preuzimanje intervencija iz baze podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        interventions = repository.getAll(new Intervention());
    }

    /**
     * Vraća listu svih intervencija nakon izvršavanja operacije. Ukoliko u
     * bazi podataka nema unetih intervencija, vraća praznu listu.
     *
     * @return lista svih intervencija, odnosno prazna lista ako nijedna
     * intervencija nije evidentirana u bazi podataka
     */
    public List<Intervention> getInterventions() {
        return interventions;
    }
    
    
}
