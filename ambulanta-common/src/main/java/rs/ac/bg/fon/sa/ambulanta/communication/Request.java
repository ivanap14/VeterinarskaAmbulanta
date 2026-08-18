/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.communication;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class Request implements Serializable{
    private Operation operation;
    private Object argument;

    public Request(Operation operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getArgument() {
        return argument;
    }
    
    
}
