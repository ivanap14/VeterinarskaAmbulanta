/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.controller;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.operation.AbstractSO;
import rs.ac.bg.fon.sa.ambulanta.operation.animal.GetAllAnimals;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.AddNewIntervention;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.EditIntervention;
import rs.ac.bg.fon.sa.ambulanta.operation.service.GetAllServices;
import rs.ac.bg.fon.sa.ambulanta.operation.veterinarian.GetAllVeterinarians;
import rs.ac.bg.fon.sa.ambulanta.operation.veterinarian.Login;

import java.util.List;


/**
 *
 * @author Korisnik
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

	public Veterinarian login(Veterinarian vet) throws Exception {
		AbstractSO operation = new Login();
        operation.execute(vet);
        Veterinarian veterinarian = ((Login)operation).getVeterinarian();
        
        return veterinarian;
	}

	public List<Veterinarian> getAllVeterinarians() throws Exception {
		AbstractSO operation = new GetAllVeterinarians();
        operation.execute(null);
        List<Veterinarian> veterinarians = ((GetAllVeterinarians)operation).getVeterinarians();
        
        return veterinarians;
	}

	public List<Animal> getAllAnimals() throws Exception {
        AbstractSO operation = new GetAllAnimals();
        operation.execute(null);
        List<Animal> animals = ((GetAllAnimals)operation).getAnimals();
        
        return animals;
    }

	public List<Service> getAllServices() throws Exception {
        AbstractSO operation = new GetAllServices();
        operation.execute(null);
        List<Service> services = ((GetAllServices)operation).getServices();
        
        return services;
    }

	public Intervention addNewIntervention(Intervention intervention) throws Exception {
        AbstractSO operation = new AddNewIntervention();
        operation.execute(intervention);
        Intervention interv = ((AddNewIntervention)operation).getIntervention();
        
        return interv;
    }

	public Intervention editIntervention(Intervention intervention) throws Exception {
        AbstractSO operation = new EditIntervention();
        operation.execute(intervention);
        Intervention interv = ((EditIntervention)operation).getIntervention();
        
        return interv;
    }

    
        
       
}

