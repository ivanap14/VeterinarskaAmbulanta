/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Klasa zadužena za izmenu podataka o postojećoj životinji u bazi podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Pre same izmene proverava da li životinja sa prosleđenim identifikatorom
 * zaista postoji u bazi podataka.
 *
 * @author Korisnik
 * @version 1.0
 */
public class EditAnimal extends AbstractSO{

	/**
    * Životinja sa izmenjenim podacima nakon uspešnog izvršavanja operacije.
    */
    private Animal animal;
    
    /**
     * Proverava da li je prosleđen parametar, da li predstavlja instancu
     * klase Animal i da li životinja sa datim identifikatorom postoji u bazi
     * podataka.
     *
     * @param param Životinja sa izmenjenim podacima. Ne sme biti null.
     * @throws Exception Ako je parametar null, nije instanca klase Animal
     * ili životinja sa prosleđenim identifikatorom ne postoji u bazi podataka.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Animal)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        Animal animal1 = (Animal) param;

        checkExist(animal1);
        

    }

    /**
     * Menja podatke prosleđene životinje u bazi podataka i čuva je kao
     * rezultat operacije.
     *
     * @param param Životinja sa izmenjenim podacima.
     * @throws Exception Ako izmena životinje u bazi podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        animal = (Animal) repository.edit((Animal)param);
    }

    /**
     * Vraća životinju sa izmenjenim podacima nakon izvršavanja operacije.
     *
     * @return izmenjena životinja
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Proverava da li životinja sa identifikatorom prosleđene životinje
     * postoji u bazi podataka.
     *
     * @param animal Životinja čije se postojanje proverava.
     * @throws Exception Ako životinja sa datim identifikatorom ne postoji
     * u bazi podataka.
     */
    private void checkExist(Animal animal) throws Exception {
        List<Animal> list = repository.getByCriteria(new Animal(),"WHERE a.id=" + animal.getId());

        if(list.isEmpty()){
            throw new Exception("Životinja ne postoji.");
        }
    }
  
}
