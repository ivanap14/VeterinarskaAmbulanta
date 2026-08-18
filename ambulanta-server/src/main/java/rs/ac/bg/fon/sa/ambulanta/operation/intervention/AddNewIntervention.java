/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class AddNewIntervention extends AbstractSO{

    private Intervention intervention;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Intervention)) {
            throw new Exception("Objekat pogresnog tipa.");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        intervention = (Intervention) repository.add((Intervention)param);
    }

    public Intervention getIntervention() {
        return intervention;
    }

    
}
