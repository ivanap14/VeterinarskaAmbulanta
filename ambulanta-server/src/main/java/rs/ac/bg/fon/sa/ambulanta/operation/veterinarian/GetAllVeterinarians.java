/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.veterinarian;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class GetAllVeterinarians extends AbstractSO{
    private List<Veterinarian> veterinarians;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        veterinarians = repository.getAll(new Veterinarian());
    }

    public List<Veterinarian> getVeterinarians() {
        return veterinarians;
    }
    
    
}
