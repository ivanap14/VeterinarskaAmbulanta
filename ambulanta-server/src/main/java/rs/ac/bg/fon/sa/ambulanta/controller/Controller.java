/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.controller;

import rs.ac.bg.fon.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.operation.AbstractSO;
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


    
        
       
}

