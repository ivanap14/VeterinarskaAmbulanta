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
 *
 * @author Korisnik
 */
public class EditAnimal extends AbstractSO{

    private Animal animal;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Animal)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        animal = (Animal) param;

        checkExist(animal);
        

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        animal = (Animal) repository.edit((Animal)param);
    }

    public Animal getAnimal() {
        return animal;
    }

    
    private void checkExist(Animal animal) throws Exception {
        List<Animal> list = repository.getByCriteria(new Animal(),"WHERE a.id=" + animal.getId());

        if(list.isEmpty()){
            throw new Exception("Životinja ne postoji.");
        }
    }
  
}
