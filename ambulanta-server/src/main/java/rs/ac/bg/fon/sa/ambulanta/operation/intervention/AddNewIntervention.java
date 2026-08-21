/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za dodavanje nove intervencije u bazu podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 *
 * @author Korisnik
 * @version 1.0
 */
public class AddNewIntervention extends AbstractSO{
	 /**
     * Intervencija koja je dodata nakon uspešnog izvršavanja operacije.
     */
    private Intervention intervention;
    
    /**
     * Proverava da li je prosleđen parametar i da li predstavlja instancu
     * klase Intervention.
     *
     * @param param Parametar koji treba da bude instanca klase Intervention.
     * Ne sme biti null.
     * @throws Exception Ako je parametar null ili nije instanca klase Intervention.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Intervention)) {
            throw new Exception("Objekat pogresnog tipa.");
        }

    }

    /**
     * Dodaje prosleđenu intervenciju u bazu podataka i čuva je kao rezultat
     * operacije.
     *
     * @param param Intervencija koja se dodaje.
     * @throws Exception Ako dodavanje intervencije u bazu podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        intervention = (Intervention) repository.add((Intervention)param);
    }

    /**
     * Vraća intervenciju dodatu nakon izvršavanja operacije.
     *
     * @return dodata intervencija
     */
    public Intervention getIntervention() {
        return intervention;
    }

    
}
