/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class GetInterventionsByInterventionCriteria extends AbstractSO{

    private List<Intervention> interventions;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        String criteria = (String) param;
        
        if(criteria.endsWith("Date")){
            String[] s = criteria.split("Date");
            criteria = s[0].toString();
            String whereSection = "WHERE i.date='"+criteria+"'";
            interventions = repository.getByCriteria(new Intervention(), whereSection); 
        }
        else if(criteria.endsWith(">")){
            String[] s = criteria.split(">");
            criteria = s[0].toString();
            String whereSection = "WHERE i.totalAmountWithDiscount>"+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
        else if(criteria.endsWith("<")){
            String[] s = criteria.split("<");
            criteria = s[0].toString();
            String whereSection = "WHERE i.totalAmountWithDiscount<"+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
        else{
            String whereSection = "WHERE i.id="+criteria;
            interventions = repository.getByCriteria(new Intervention(), whereSection);
        }
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }
    
}
