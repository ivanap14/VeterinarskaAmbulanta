/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.specialization;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za dodavanje nove specijalizacije u bazu podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Pre dodavanja proverava da li specijalizacija sa istim nazivom već postoji
 * u bazi podataka.
 *
 * @author Korisnik
 * @version 1.0
 */
public class AddNewSpecialization extends AbstractSO{
	
	/**
     * Specijalizacija koja je dodata nakon uspešnog izvršavanja operacije.
     */
    private Specialization specialization;
    
    /**
     * Proverava da li je prosleđen parametar, da li predstavlja instancu
     * klase Specialization i da li specijalizacija sa istim nazivom već
     * postoji u bazi podataka.
     *
     * @param param Specijalizacija koja se dodaje. Ne sme biti null.
     * @throws Exception Ako je parametar null, nije instanca klase
     * Specialization ili specijalizacija sa istim nazivom već postoji u
     * bazi podataka.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Specialization)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        specialization = (Specialization) param;
        
        checkExistName(specialization);
    }

    /**
     * Dodaje prosleđenu specijalizaciju u bazu podataka i čuva je kao
     * rezultat operacije.
     *
     * @param param Specijalizacija koja se dodaje.
     * @throws Exception Ako dodavanje specijalizacije u bazu podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        specialization = (Specialization) repository.add((Specialization)param);
    }

    /**
     * Vraća specijalizaciju dodatu nakon izvršavanja operacije.
     *
     * @return dodata specijalizacija
     */
    public Specialization getSpecialization() {
        return specialization;
    }

    /**
     * Proverava da li specijalizacija sa nazivom prosleđene specijalizacije
     * već postoji u bazi podataka.
     *
     * @param specialization Specijalizacija čiji se naziv proverava.
     * @throws Exception Ako specijalizacija sa datim nazivom već postoji
     * u bazi podataka.
     */
    private void checkExistName(Specialization specialization) throws Exception {
        List<Specialization> list = repository.getByCriteria(new Specialization(),"WHERE name='" + specialization.getName()+"'");
        
        if(!list.isEmpty()){
            throw new Exception("Specijalizacija sa unetim imenom već postoji.");
        }
        
    }
    
}
