/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.threads;

import rs.ac.bg.fon.sa.ambulanta.communication.*;
import rs.ac.bg.fon.sa.ambulanta.controller.*;
import rs.ac.bg.fon.sa.ambulanta.domain.*;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Korisnik
 */

public class HandleClientThread extends Thread{
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    
    private Veterinarian user;

    private ServerThread server;
    
    public HandleClientThread(Socket socket, ServerThread server) throws IOException {
        this.socket = socket;
        this.server=server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        
    }

    @Override
    public void run() {
        try {
            while(!socket.isClosed()){      
                Request request = (Request) receiver.receive();
                Response response=handleRequest(request);
                sender.send(response);
            }   
        } catch (Exception ex) {
            server.logout(this);
            
        }
    }


    private Response handleRequest(Request request){
        Response response = new Response();
        try {
            switch (request.getOperation()) {
	            case LOGIN:
	                Veterinarian vet = (Veterinarian) request.getArgument();
	                vet = Controller.getInstance().login(vet);
	                if(server.isAlreadyLoggedIn(vet)){
	                    throw new Exception("Korisnik je već ulogovan!");
	                }
	                user=vet;
	                response.setResult(vet);
	                break;
	            case GET_ALL_VETERINARIANS:
                    List<Veterinarian> veterinarians = Controller.getInstance().getAllVeterinarians();
                    response.setResult(veterinarians);
                    break;
                case GET_ALL_ANIMALS:
                    List<Animal> animals = Controller.getInstance().getAllAnimals();
                    response.setResult(animals);
                    break;
                case GET_ALL_SERVICES:
                    List<Service> services = Controller.getInstance().getAllServices();
                    response.setResult(services);
                    break;
                case ADD_NEW_INTERVENTION:
                    Intervention intervention =  (Intervention) request.getArgument();
                    intervention = Controller.getInstance().addNewIntervention(intervention);
                    response.setResult(intervention);
                    break;
                case EDIT_INTERVENTION:
                    Intervention intervention1 =  (Intervention) request.getArgument();
                    intervention1 = Controller.getInstance().editIntervention(intervention1);
                    response.setResult(intervention1);
                    break;
               
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            response.setException(e);
        }
       
        return response;
    }
    
    public Veterinarian getUser() {
        return user;
    }
    
    public Socket getSocket() {
        return socket;
    }
}
