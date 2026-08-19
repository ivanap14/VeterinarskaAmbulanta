/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.controller;



import rs.ac.bg.fon.sa.ambulanta.communication.*;
import rs.ac.bg.fon.sa.ambulanta.domain.*;

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

	public Veterinarian login(Veterinarian vet) throws Exception {
		Request request = new Request(Operation.LOGIN, vet);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (Veterinarian) response.getResult();
        }else{
            throw response.getException();
        } 
	}

	public List<Veterinarian> getAllVeterinarians() throws Exception {
		Request request = new Request(Operation.GET_ALL_VETERINARIANS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Veterinarian>)response.getResult();
        }else{
            throw response.getException();
        }
	}
	
	public List<Animal> getAllAnimals() throws Exception {
		Request request = new Request(Operation.GET_ALL_ANIMALS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Animal>)response.getResult();
        }else{
            throw response.getException();
        }
	}

	public List<Service> getAllServices() throws Exception {
		Request request = new Request(Operation.GET_ALL_SERVICES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Service>)response.getResult();
        }else{
            throw response.getException();
        }
	}

	public Intervention addNewIntervention(Intervention intervention) throws Exception {
		Request request = new Request(Operation.ADD_NEW_INTERVENTION, intervention);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Intervention) response.getResult();
        }else{
            throw response.getException();
        } 
	}

	public Intervention editIntervention(Intervention intervention) throws Exception {
		Request request = new Request(Operation.EDIT_INTERVENTION, intervention);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Intervention) response.getResult();
        }else{
            throw response.getException();
        } 
	}

	public List<Intervention> getInterventionsByInterventionCriteria(String criteria) throws Exception {
        Request request = new Request(Operation.GET_INTERVENTIONS_BY_INTERVENTION_CRITERIA, criteria);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Intervention>)response.getResult();
        }else{
            throw response.getException();
        }
    }

	public List<Intervention> getInterventionsByVeterinarianCriteria(String criteria) throws Exception {
        Request request = new Request(Operation.GET_INTERVENTIONS_BY_VETERINARIAN_CRITERIA, criteria);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Intervention>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Intervention> getInterventionsByAnimalCriteria(String criteria) throws Exception {
        Request request = new Request(Operation.GET_INTERVENTIONS_BY_ANIMAL_CRITERIA, criteria);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Intervention>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Intervention> getInterventionsByServiceCriteria(String criteria) throws Exception {
        Request request = new Request(Operation.GET_INTERVENTIONS_BY_SERVICE_CRITERIA, criteria);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Intervention>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public List<Intervention> getAllInterventions() throws Exception {
        Request request = new Request(Operation.GET_ALL_INTERVENTIONS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Intervention>)response.getResult();
        }else{
            throw response.getException();
        }
    }

    public Intervention getIntervention(Intervention intervention) throws Exception {
        Request request = new Request(Operation.GET_INTERVENTION, intervention);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Intervention) response.getResult();
        }else{
            throw response.getException();
        }
    }
	
    public List<Owner> getAllOwners() throws Exception {
        Request request = new Request(Operation.GET_ALL_OWNERS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Owner>)response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public Animal addNewAnimal(Animal animal) throws Exception {
        Request request = new Request(Operation.ADD_NEW_ANIMAL, animal);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Animal) response.getResult();
        }else{
            throw response.getException();
        }
    }

    public Animal editAnimal(Animal animal) throws Exception {
        Request request = new Request(Operation.EDIT_ANIMAL, animal);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Animal) response.getResult();
        }else{
            throw response.getException();
        } 
    }

    public Specialization addNewSpecialization(Specialization specialization) throws Exception {
        Request request = new Request(Operation.ADD_NEW_SPECIALIZATION, specialization);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return  (Specialization) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
}
