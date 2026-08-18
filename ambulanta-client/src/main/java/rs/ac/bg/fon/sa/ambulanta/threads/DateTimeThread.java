/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.threads;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

/**
 *
 * @author Korisnik
 */
public class DateTimeThread extends Thread{

    JLabel lblDate;
    JLabel lblTime;
    
    public DateTimeThread(JLabel lblDate, JLabel lblTime) {
        this.lblDate=lblDate;
        this.lblTime=lblTime;
    }

    @Override
    public void run() {
        while(true){
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            
            DateTimeFormatter formatD = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DateTimeFormatter formatT = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            lblDate.setText(date.format(formatD));
            lblTime.setText(time.format(formatT));
            
        }
    }
    
}
