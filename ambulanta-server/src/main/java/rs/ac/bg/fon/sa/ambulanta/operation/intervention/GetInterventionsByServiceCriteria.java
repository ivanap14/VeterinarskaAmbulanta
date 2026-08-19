/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class GetInterventionsByServiceCriteria extends AbstractSO{
    private List<Intervention> interventions;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        interventions = new ArrayList<>();
        
        String criteria = (String) param;
        String whereSection = "WHERE s.name LIKE '%"+criteria+"%'";
        
        List<InterventionItem> items = repository.getByCriteria(new InterventionItem(), whereSection);
        
        for (InterventionItem item : items) {
            String whereSection1 = "WHERE i.id="+item.getIntervention().getId();
            List<Intervention> result = repository.getByCriteria(new Intervention(), whereSection1);

            if (!result.isEmpty()) {
                interventions.add(result.get(0));
            }
        }
        
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }
}
