/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.controller;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.operation.AbstractSO;
import rs.ac.bg.fon.sa.ambulanta.operation.animal.AddNewAnimal;
import rs.ac.bg.fon.sa.ambulanta.operation.animal.EditAnimal;
import rs.ac.bg.fon.sa.ambulanta.operation.animal.GetAllAnimals;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.AddNewIntervention;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.EditIntervention;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.GetAllInterventions;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.GetIntervention;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.GetInterventionsByAnimalCriteria;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.GetInterventionsByInterventionCriteria;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.GetInterventionsByServiceCriteria;
import rs.ac.bg.fon.sa.ambulanta.operation.intervention.GetInterventionsByVeterinarianCriteria;
import rs.ac.bg.fon.sa.ambulanta.operation.owner.GetAllOwners;
import rs.ac.bg.fon.sa.ambulanta.operation.service.GetAllServices;
import rs.ac.bg.fon.sa.ambulanta.operation.specialization.AddNewSpecialization;
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

	public List<Intervention> getInterventionsByInterventionCriteria(String criteria) throws Exception {
        AbstractSO operation = new GetInterventionsByInterventionCriteria();
        operation.execute(criteria);
        List<Intervention> interventions = ((GetInterventionsByInterventionCriteria)operation).getInterventions();
        
        return interventions;
    }

    public List<Intervention> getInterventionsByVeterinarianCriteria(String criteria) throws Exception {
        AbstractSO operation = new GetInterventionsByVeterinarianCriteria();
        operation.execute(criteria);
        List<Intervention> interventions = ((GetInterventionsByVeterinarianCriteria)operation).getInterventions();
        
        return interventions;
    }

    public List<Intervention> getInterventionsByAnimalCriteria(String criteria) throws Exception {
        AbstractSO operation = new GetInterventionsByAnimalCriteria();
        operation.execute(criteria);
        List<Intervention> interventions = ((GetInterventionsByAnimalCriteria)operation).getInterventions();
        
        return interventions;
    }

    public List<Intervention> getInterventionsByServiceCriteria(String criteria) throws Exception {
        AbstractSO operation = new GetInterventionsByServiceCriteria();
        operation.execute(criteria);
        List<Intervention> interventions = ((GetInterventionsByServiceCriteria)operation).getInterventions();
        
        return interventions;
    }

    public List<Intervention> getAllInterventions() throws Exception {
        AbstractSO operation = new GetAllInterventions();
        operation.execute(null);
        List<Intervention> interventions = ((GetAllInterventions)operation).getInterventions();
        
        return interventions;
    }

    public Intervention getIntervention(Intervention intervention) throws Exception {
        AbstractSO operation = new GetIntervention();
        operation.execute(intervention);
        Intervention interv = ((GetIntervention)operation).getIntervention();
        
        return interv;
    }
        
    public List<Owner> getAllOwners() throws Exception {
        AbstractSO operation = new GetAllOwners();
        operation.execute(null);
        List<Owner> owners = ((GetAllOwners)operation).getOwners();
        
        return owners;
    }


    public Animal addNewAnimal(Animal animal) throws Exception {
        AbstractSO operation = new AddNewAnimal();
        operation.execute(animal);
        Animal a = ((AddNewAnimal)operation).getAnimal();
        
        return a;
    }

    public Animal editAnimal(Animal animal) throws Exception {
        AbstractSO operation = new EditAnimal();
        operation.execute(animal);
        Animal a = ((EditAnimal)operation).getAnimal();
        
        return a;
    }
    
    public Specialization addNewSpecialization(Specialization specialization) throws Exception {
        AbstractSO operation = new AddNewSpecialization();
        operation.execute(specialization);
        Specialization s = ((AddNewSpecialization)operation).getSpecialization();
        
        return s;
    }
}

