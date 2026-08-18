/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class EditIntervention extends AbstractSO{

    private Intervention intervention;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Intervention)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        intervention = (Intervention) param;

        checkExist(intervention);
         
        checkOtherConstraints(intervention);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         
        intervention = (Intervention) param;

        InterventionItem deleteItem = new InterventionItem();
        deleteItem.setIntervention(intervention);

        repository.delete(deleteItem);

        intervention = (Intervention) repository.edit(intervention);

        List<InterventionItem> items = intervention.getInterventionItems();

        for (InterventionItem item : items) {
            repository.add(item);
        }
    }

    public Intervention getIntervention() {
        return intervention;
    }
    
    private void checkExist(Intervention intervention) throws Exception {
        List<Intervention> list = repository.getByCriteria(new Intervention(),"WHERE i.id=" + intervention.getId());

        if(list.isEmpty()){
            throw new Exception("Intervencija ne postoji.");
        }
    }
    

    private void checkOtherConstraints(Intervention intervention1) throws Exception {
        if (intervention.getInterventionItems().isEmpty()) {
            throw new Exception("Intervencija mora sadržati najmanje jednu uslugu.");
        }
    }

    
    
}
