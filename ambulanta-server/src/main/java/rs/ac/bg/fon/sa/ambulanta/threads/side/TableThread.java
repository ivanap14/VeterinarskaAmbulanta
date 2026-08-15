/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.threads.side;

import rs.ac.bg.fon.sa.ambulanta.form.*;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class TableThread extends Thread{

    FrmMain form;
    
    public TableThread(FrmMain form) {
        this.form=form;
    }

    @Override
    public void run() {
        while (true) {            
            form.refreshTable();
            
            try {
                sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(TableThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
