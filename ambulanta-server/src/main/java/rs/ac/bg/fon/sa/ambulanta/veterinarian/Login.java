/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.veterinarian;

import rs.ac.bg.fon.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class Login extends AbstractSO{

    private Veterinarian veterinarian;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Parametar nije prosledjen.");
        }
        if (!(param instanceof Veterinarian)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
        Veterinarian vetToLogin = (Veterinarian) param;
        
        if (vetToLogin.getEmail().isEmpty()) {
            throw new Exception("Nije unet email.");
        }
        
        if (vetToLogin.getPassword().isEmpty()) {
            throw new Exception("Nije uneta šifra.");
        }
        
        if (!(param instanceof Veterinarian)) {
            throw new Exception("Objekat pogresnog tipa.");
        }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Veterinarian vetToLogin = (Veterinarian) param;
        List<Veterinarian> veterinarians=repository.getAll(vetToLogin);
        for (Veterinarian v : veterinarians) {
            if(v.getEmail().equals(vetToLogin.getEmail()) && v.getPassword().equals(vetToLogin.getPassword())){
                veterinarian=v;
                return;
            }
        }
        throw new Exception("Korisničko ime i šifra nisu ispravni!");
        
    }
    
    public Veterinarian getVeterinarian() {
        return veterinarian;
    }
    

}
    
    