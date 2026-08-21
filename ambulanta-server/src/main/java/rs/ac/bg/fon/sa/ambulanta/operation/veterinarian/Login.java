/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.veterinarian;

import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za prijavu (login) veterinara na osnovu
 * njegove email adrese i šifre.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 *
 * @author Korisnik
 * @version 1.0
 */
public class Login extends AbstractSO{

	/**
     * Veterinar koji je uspešno prijavljen nakon izvršavanja operacije.
     */
    private Veterinarian veterinarian;
    
    /**
     * Proverava da li je prosleđen parametar i da li predstavlja instancu
     * klase Veterinarian.
     *
     * @param param Veterinar sa unetom email adresom i šifrom. Ne sme biti null.
     * @throws Exception Ako parametar nije prosleđen ili nije instanca klase
     * Veterinarian.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Parametar nije prosledjen.");
        }
        if (!(param instanceof Veterinarian)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
    }

    /**
     * Preuzima sve veterinare iz baze podataka i traži onog čija se email
     * adresa i šifra poklapaju sa prosleđenim podacima. Ukoliko pronađe
     * odgovarajućeg veterinara, čuva ga kao rezultat operacije, u
     * suprotnom baca izuzetak.
     *
     * @param param Veterinar sa unetom email adresom i šifrom koje se
     * proveravaju.
     * @throws Exception Ako preuzimanje veterinara iz baze podataka ne uspe,
     * ili ako uneta email adresa i/ili šifra nisu ispravni.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Veterinarian vetToLogin = (Veterinarian) param;
        List<Veterinarian> veterinarians=repository.getAll(vetToLogin);
        for (Veterinarian v : veterinarians) {
            if(v.getEmail().equals(vetToLogin.getEmail()) && v.getPassword().equals(vetToLogin.getPassword())){
                veterinarian=v;
                return;
            }
        }
        throw new Exception("Korisničko ime i/ili šifra nisu ispravni!");
        
    }
    
    /**
     * Vraća veterinara koji je uspešno prijavljen nakon izvršavanja operacije.
     *
     * @return prijavljeni veterinar
     */
    public Veterinarian getVeterinarian() {
        return veterinarian;
    }
    

}
    
    