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
public class GetAllSpecializations extends AbstractSO{
   private List<Specialization> specializations;

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        specializations = repository.getAll(new Specialization());
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    } 
}
