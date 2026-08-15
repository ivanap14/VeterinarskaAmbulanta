/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ambulanta.controller;



import rs.ac.bg.fon.ambulanta.communication.*;
import rs.ac.bg.fon.ambulanta.domain.*;
import java.net.Socket;
import java.util.List;



/**
 *
 * @author Korisnik
 */
public class Controller {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Controller instance;
    
    private Veterinarian currentUser;
    
    private Controller(){
    }
    
    public static Controller getInstance(){
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }
    
    public void setSocket(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
     public Veterinarian getCurrentUser() {
        return currentUser;
    }

     public void setCurrentUser(Veterinarian vet) {
        this.currentUser=vet;
    }


    
}
