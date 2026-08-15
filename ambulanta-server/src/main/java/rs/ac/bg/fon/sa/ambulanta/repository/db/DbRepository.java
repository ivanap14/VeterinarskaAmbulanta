/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.repository.db;

import java.io.IOException;
import java.sql.SQLException;

import rs.ac.bg.fon.sa.ambulanta.repository.Repository;


/**
 *
 * @author Korisnik
 */
public interface DbRepository<T> extends Repository<T> {
    default public void connect() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
