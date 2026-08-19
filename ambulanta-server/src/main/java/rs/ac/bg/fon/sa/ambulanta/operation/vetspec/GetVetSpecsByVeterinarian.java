/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.vetspec;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;


/**
 *
 * @author Korisnik
 */
public class GetVetSpecsByVeterinarian extends AbstractSO{

    List<VetSpec> vetSpecs;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Veterinarian vet = (Veterinarian)param;
        String whereSection = "WHERE idVeterinarian="+vet.getId();
        vetSpecs = repository.getByCriteria(new VetSpec(), whereSection);
    }

    public List<VetSpec> getVetSpecs() {
        return vetSpecs;
    }
    
    
    
}
