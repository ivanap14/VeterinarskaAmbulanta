/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za zadužena za preuzimanje svih životinja iz baze podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Operacija ne zahteva ulazni parametar niti proveru preduslova.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetAllAnimals extends AbstractSO{
	/**
     * Lista svih životinja preuzeta nakon uspešnog izvršavanja operacije.
     */
	private List<Animal> animals;

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
     * Preuzima sve životinje iz baze podataka i čuva ih kao rezultat operacije.
     *
     * @param param Vrednost null.
     * @throws Exception Ako preuzimanje životinja iz baze podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        animals = repository.getAll(new Animal());
    }

    /**
     * Vraća listu svih životinja nakon izvršavanja operacije. Ukoliko u bazi
     * podataka nema unetih životinja, vraća praznu listu.
     *
     * @return lista svih životinja, odnosno prazna lista ako nijedna
     * životinja nije evidentirana u bazi podataka
     */
    public List<Animal> getAnimals() {
        return animals;
    }
 
}
