/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.repository.db;

import rs.ac.bg.fon.sa.ambulanta.constant.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Korisnik
 */
public class DbConnectionFactory {
    private Connection connection;
    private static DbConnectionFactory instance;
    
    private DbConnectionFactory(){
        
    }
    
    public static DbConnectionFactory getInstance(){
        if(instance==null){
            instance=new DbConnectionFactory();
        }
        return instance;
    }
     
     
    public Connection getConnection() throws SQLException,IOException{
       
        if (connection == null || connection.isClosed()) {
            try {

                Properties properties = new Properties();
                properties.load(new FileInputStream(MyServerConstants.DB_CONFIG_FILE_PATH));
   
                String url = properties.getProperty(MyServerConstants.DB_CONFIG_URL);
                String user = properties.getProperty(MyServerConstants.DB_CONFIG_USERNAME);
                String password = properties.getProperty(MyServerConstants.DB_CONFIG_PASSWORD);
                
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije sa bazom!\n" + ex.getMessage());
                throw ex;
            }
        }
        return connection;
    }
}
