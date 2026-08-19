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
public class EditInterventionThread extends Thread{
    
    FrmViewEditIntervention form;

    public EditInterventionThread(FrmViewEditIntervention form) {
        this.form = form;
    }

    @Override
    public void run() {
        while (true) {    
            form.updateVeterinarianAnimalOwner();
            form.populateTextFieldsForDiscountAndTotals();
            
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(EditInterventionThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
