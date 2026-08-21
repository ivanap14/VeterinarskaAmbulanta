/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za dodavanje nove životinje u bazu podataka.
 *
 * Nasleđuej apstarktnu kalsu AbstractSO i implementira metode 
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 *
 * @author Korisnik
 * @version 1.0
 */
public class AddNewAnimal extends AbstractSO{
    
    /**
     * Životinja koja je dodata nakon uspešnog izvršavanja operacije.
     */
    private Animal animal;

    /**
     * Proverava da li je prosleđen parametar i da li predstavlja instancu klase Animal.
     *
     * @param param Parametar koji treba da bude instanca klase Animal. Ne sme biti null.
     * @throws Exception Ako je parametar null ili nije instanca klase Animal.
     */

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Animal)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
    }

    /**
     * Dodaje prosleđenu životinju u bazu podataka i čuva je kao rezultat
     * operacije.
     *
     * @param param Životinja koja se dodaje.
     * @throws Exception Ako dodavanje životinje u bazu podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        animal = (Animal) repository.add((Animal)param);
    }

    /**
     * Vraća životinju dodatu nakon izvršavanja operacije.
     *
     * @return dodata životinja
     */
    public Animal getAnimal() {
        return animal;
    }
    
    
}
