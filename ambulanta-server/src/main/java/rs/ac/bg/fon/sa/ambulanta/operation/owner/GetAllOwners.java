/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.owner;


import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.time.LocalDate;
import rs.ac.bg.fon.sa.ambulanta.operation.*;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Korisnik
 */
public class GetAllOwners extends AbstractSO{

    private List<Owner> owners;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        owners = repository.getAll(new Owner());
    }

    public List<Owner> getOwners() {
        return owners;
    }
    
}
