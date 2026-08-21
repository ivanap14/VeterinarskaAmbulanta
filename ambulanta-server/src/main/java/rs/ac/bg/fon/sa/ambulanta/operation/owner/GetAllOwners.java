/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.owner;


import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;
import java.time.LocalDate;
import java.util.List;
/**
 * Klasa zadužena za preuzimanje svih vlasnika iz baze podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Operacija ne zahteva ulazni parametar niti proveru preduslova.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetAllOwners extends AbstractSO{
	/**
     * Lista svih vlasnika preuzeta nakon uspešnog izvršavanja operacije.
     */
    private List<Owner> owners;
    
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
     * Preuzima sve vlasnike iz baze podataka i čuva ih kao rezultat operacije.
     *
     * @param param Vrednost null.
     * @throws Exception Ako preuzimanje vlasnika iz baze podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        owners = repository.getAll(new Owner());
    }

    /**
     * Vraća listu svih vlasnika nakon izvršavanja operacije. Ukoliko u bazi
     * podataka nema unetih vlasnika, vraća praznu listu.
     *
     * @return lista svih vlasnika, odnosno prazna lista ako nijedan vlasnik
     * nije evidentiran u bazi podataka
     */
    public List<Owner> getOwners() {
        return owners;
    }
    
}
