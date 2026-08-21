/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.service;


import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za preuzimanje svih usluga iz baze podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Operacija ne zahteva ulazni parametar niti proveru preduslova.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetAllServices extends AbstractSO{
	/**
     * Lista svih usluga preuzeta nakon uspešnog izvršavanja operacije.
     */
	private List<Service> services;

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
     * Preuzima sve usluge iz baze podataka i čuva ih kao rezultat operacije.
     *
     * @param param Vrednsot null.
     * @throws Exception Ako preuzimanje usluga iz baze podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        services = repository.getAll(new Service());
    }

    /**
     * Vraća listu svih usluga nakon izvršavanja operacije. Ukoliko u bazi
     * podataka nema unetih usluga, vraća praznu listu.
     *
     * @return lista svih usluga, odnosno prazna lista ako nijedna usluga
     * nije evidentirana u bazi podataka
     */
    public List<Service> getServices() {
        return services;
    }

    
}
