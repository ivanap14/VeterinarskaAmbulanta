/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.threads;

import rs.ac.bg.fon.sa.ambulanta.forms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class InterventionThread extends Thread{

    FrmNewIntervention form;
    
    public InterventionThread(FrmNewIntervention form) {
        this.form=form;
    }

    @Override
    public void run() {
        while (true) {            
            form.updateVeterinarianAndAnimal();
            form.populateTextFieldsForDiscountAndTotals();
            
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(InterventionThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
    
}
