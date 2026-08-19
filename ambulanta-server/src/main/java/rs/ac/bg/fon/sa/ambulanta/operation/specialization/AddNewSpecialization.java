/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.specialization;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class AddNewSpecialization extends AbstractSO{

    private Specialization specialization;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Specialization)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        specialization = (Specialization) param;
        
        checkExistName(specialization);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        specialization = (Specialization) repository.add((Specialization)param);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    private void checkExistName(Specialization specialization) throws Exception {
        List<Specialization> list = repository.getByCriteria(new Specialization(),"WHERE name='" + specialization.getName()+"'");
        
        if(!list.isEmpty()){
            throw new Exception("Specijalizacija sa unetim imenom već postoji.");
        }
        
    }
    
}
