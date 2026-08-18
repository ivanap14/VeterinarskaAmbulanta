/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class GetAllAnimals extends AbstractSO{
     private List<Animal> animals;

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        animals = repository.getAll(new Animal());
    }

    public List<Animal> getAnimals() {
        return animals;
    }
 
}
