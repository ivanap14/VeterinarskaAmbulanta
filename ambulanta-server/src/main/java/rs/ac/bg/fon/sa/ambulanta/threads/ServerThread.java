/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.threads;

import rs.ac.bg.fon.ambulanta.domain.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Korisnik
 */

public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private List<HandleClientThread> clients;
    
    

    public ServerThread() throws IOException {
        serverSocket=new ServerSocket(9000);
        clients=new ArrayList<>();
    }
    
    @Override
    public void run() {
        try {
            while(!serverSocket.isClosed()){
                 System.out.println("Cekam klijente...");
                 Socket socket=serverSocket.accept();
                 HandleClientThread thread=new HandleClientThread(socket,this);
                 thread.start();
                 clients.add(thread);
                 System.out.println("Klijent se povezao!");
            }
        } catch (IOException ex) {
             
        }
        System.out.println("Server je zaustavljen!");
    }
    
    public void stopServer() throws IOException{
        for (HandleClientThread client : clients) {
            client.getSocket().close();
        }
        clients.clear();
        
        serverSocket.close();
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public List<Veterinarian> getLoggedInClients() {
        List<Veterinarian> users = new ArrayList<>();
        for (HandleClientThread client : clients) {
            if(client.getUser()!=null){
                users.add(client.getUser());
            }
        }
        return users;
    }

    void logout(HandleClientThread thread) {
        try {
            thread.getSocket().close();
            clients.remove(thread);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    boolean isAlreadyLoggedIn() {
//        List<Veterinarian> users = getLoggedInClients();
//        for (Veterinarian user : users) {
//            if(user.getEmail().equals(user))
//        }
//    }

    boolean isAlreadyLoggedIn(Veterinarian vet) {
         List<Veterinarian> users = getLoggedInClients();
        for (Veterinarian user : users) {
            if(user.getEmail().equals(vet.getEmail())) return true;
        }
        return false;
    }
    
    
    
}
