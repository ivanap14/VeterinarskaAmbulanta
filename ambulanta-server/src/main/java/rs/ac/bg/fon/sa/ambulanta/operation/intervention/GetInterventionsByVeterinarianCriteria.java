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
public class GetInterventionsByVeterinarianCriteria extends AbstractSO{
    private List<Intervention> interventions;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        String criteria = (String) param;
        
        if(criteria.endsWith("Lastname")){
            String[] s = criteria.split("Lastname");
            criteria = s[0].toString();
            String whereSection = "WHERE v.lastname LIKE '"+criteria+"%'";
            interventions = repository.getByCriteria(new Intervention(), whereSection); 
        }
        else{
            String whereSection = "WHERE i.idVeterinarian="+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }
}
