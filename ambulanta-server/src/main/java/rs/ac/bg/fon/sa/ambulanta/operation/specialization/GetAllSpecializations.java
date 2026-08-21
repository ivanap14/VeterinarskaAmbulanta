/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.specialization;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;


/**
 * Klasa zadužena za preuzimanje svih specijalizacija iz baze
 * podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Operacija ne zahteva ulazni parametar niti proveru preduslova.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetAllSpecializations extends AbstractSO{
	/**
	* Lista svih specijalizacija preuzeta nakon uspešnog izvršavanja operacije.
	*/
	private List<Specialization> specializations;

	/**
     * Ne vrši nikakvu proveru s obzirom na to da operacija ne zahteva
     * ulazni parametar.
     *
     * @param param Vrednost null.
     * @throws Exception Nikada se ne baca.
     */
    @Override
    protected void preconditions(Object param) throws Exception {

    }

    /**
     * Preuzima sve specijalizacije iz baze podataka i čuva ih kao rezultat
     * operacije.
     *
     * @param param Vrednost null.
     * @throws Exception Ako preuzimanje specijalizacija iz baze podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        specializations = repository.getAll(new Specialization());
    }

    /**
     * Vraća listu svih specijalizacija nakon izvršavanja operacije. Ukoliko
     * u bazi podataka nema unetih specijalizacija, vraća praznu listu.
     *
     * @return lista svih specijalizacija, odnosno prazna lista ako nijedna
     * specijalizacija nije evidentirana u bazi podataka
     */
    public List<Specialization> getSpecializations() {
        return specializations;
    } 
}
