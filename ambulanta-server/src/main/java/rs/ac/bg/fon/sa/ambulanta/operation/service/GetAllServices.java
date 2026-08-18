/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.service;


import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class GetAllServices extends AbstractSO{
    private List<Service> services;

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        services = repository.getAll(new Service());
    }

    public List<Service> getServices() {
        return services;
    }

    
}
