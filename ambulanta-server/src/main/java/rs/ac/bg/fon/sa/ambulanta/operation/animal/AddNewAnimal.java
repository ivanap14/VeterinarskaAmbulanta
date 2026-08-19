/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class AddNewAnimal extends AbstractSO{
    
    private Animal animal;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Animal)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        animal = (Animal) repository.add((Animal)param);
    }

    public Animal getAnimal() {
        return animal;
    }
    
    
}
