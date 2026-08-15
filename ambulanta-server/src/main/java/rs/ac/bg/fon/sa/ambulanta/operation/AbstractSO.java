/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.operation;

import rs.ac.bg.fon.sa.ambulanta.repository.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.*;

/**
 *
 * @author Korisnik
 */
public abstract class AbstractSO {
    
    protected final Repository repository;

    public AbstractSO() {
        this.repository = new DbBroker();
    }
    public void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
            System.out.println("Uspesno izvrsena operacija!!!");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Neuspesno izvrsena operacija!!!");
            rollbackTransaction();
            throw exception;
        }
    }

    protected abstract void preconditions(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository)repository).connect();
    }

    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository)repository).commit();
        }

    private void rollbackTransaction() throws Exception {
        ((DbRepository)repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository)repository).disconnect();
    }
}
