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
public class GetIntervention extends AbstractSO{

    private Intervention intervention;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        Intervention intervention1 = (Intervention) param;
        
        List<Intervention> interventions = repository.getByCriteria(param, "WHERE i.id="+intervention1.getId());
        
        intervention = interventions.get(0);
        
        List<InterventionItem> items = repository.getByCriteria(new InterventionItem(), "WHERE idIntervention="+intervention.getId());
        intervention.setInterventionItems(items);
        
    }

    public Intervention getIntervention() {
        return intervention;
    }
    
}
