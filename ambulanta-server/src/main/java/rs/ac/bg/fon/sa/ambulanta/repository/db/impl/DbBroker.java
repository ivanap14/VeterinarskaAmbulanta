/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.repository.db.impl;

import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.repository.db.*;
import java.sql.*;
import java.util.ArrayList;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Generička implementacija pristupa relacionoj bazi podataka putem JDBC-a.
 *
 * Implementira generičke CRUD (add, edit, delete, getAll, getByCriteria) operacije koje
 * rade nad bilo kojim entitetom koji implementira interfejs GenericEntity.
 * SQL upiti se dinamički generišu na osnovu metapodataka koje svaki konkretan
 * entitet sam pruža (naziv tabele, alias, kolone, uslovi pretrage i sl.),
 * čime se izbegava pisanje posebne implementacije operacija za svaki
 * pojedinačni entitet.
 *
 * @author Korisnik
 * @version 1.0
 */
public class DbBroker implements DbRepository<GenericEntity>{

	/**
     * Dodaje prosleđeni entitet u odgovarajuću tabelu baze podataka.
     * Nakon uspešnog izvršavanja INSERT upita, automatski generisani
     * identifikator se preuzima iz baze podataka i postavlja na entitet.
     *
     * @param entity Entitet koji se dodaje u bazu podataka.
     * @return dodati entitet, sa postavljenim identifikatorom dobijenim
     * iz baze podataka
     * @throws Exception Ako izvršavanje INSERT upita nad bazom podataka ne uspe.
     */
    @Override
    public GenericEntity add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append("(").append(entity.getColumnNamesForInsert()).append(")")
                    .append("VALUES (").append(entity.getInsertValues()).append(")");
            String query = sb.toString();
            
            System.out.println(query);
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if(rsKey.next()){
                Long id = rsKey.getLong(1);
                entity.setIdFromRS(id);
            }
            statement.close();
            rsKey.close();
            
            return entity;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Menja podatke prosleđenog entiteta u odgovarajućoj tabeli baze
     * podataka, na osnovu uslova pretrage koji entitet sam definiše.
     *
     * @param entity Entitet sa izmenjenim podacima koji se ažurira u bazi
     * podataka.
     * @return izmenjeni entitet
     * @throws Exception Ako izvršavanje UPDATE upita nad bazom podataka ne uspe.
     */
    @Override
    public GenericEntity edit(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ")
                    .append(entity.setAttributeValues())
                    .append(" WHERE ")
                    .append(entity.getQueryCondition());
            String query = sb.toString();
            
            System.out.println(query);
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
                
            statement.close();
            
            return entity;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Briše prosleđeni entitet iz odgovarajuće tabele baze podataka, na
     * osnovu uslova pretrage koji entitet sam definiše.
     *
     * @param entity Entitet čiji se odgovarajući zapis briše iz baze
     * podataka.
     * @return obrisani entitet
     * @throws Exception Ako izvršavanje DELETE upita nad bazom podataka ne uspe.
     */
    @Override
    public GenericEntity delete(GenericEntity entity) throws Exception {
        try {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                .append(entity.getTableName())
                .append(" WHERE ")
                .append(entity.getQueryCondition());
        String query = sb.toString();
        
        System.out.println(query);

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        statement.close();

        return entity;

    } catch(SQLException ex){
        ex.printStackTrace();
        throw ex;
    }
    }

    /**
     * Preuzima sve zapise iz tabele koja odgovara prosleđenom entitetu,
     * uključujući i eventualne JOIN upite koje entitet sam definiše.
     *
     * @param entity Instanca entiteta koja se koristi za dobijanje naziva
     * tabele, aliasa i JOIN dela upita (sadržaj njenih atributa se ne koristi).
     * @return lista svih pronađenih entiteta, odnosno prazna lista ako
     * odgovarajuća tabela ne sadrži nijedan zapis
     * @throws Exception Ako izvršavanje SELECT upita nad bazom podataka ne uspe.
     */
    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        try {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        List<GenericEntity> list= new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getTableName()).append(" ").append(entity.getTableAlias()).append(" ")
                .append(entity.getJoinQuery());
        String query = sb.toString();
        
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs =statement.executeQuery(query);
        while(rs.next()){
                list.add(entity.getEntityFromResultSet(rs));
            }
        rs.close();
        statement.close();
        return list;
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    /**
     * Preuzima sve zapise iz tabele koja odgovara prosleđenom entitetu, koji
     * dodatno zadovoljavaju prosleđeni uslov pretrage (WHERE deo upita).
     * Uzima u obzir i eventualne JOIN upite koje entitet sam definiše.
     *
     * @param entity Instanca entiteta koja se koristi za dobijanje naziva
     * tabele, aliasa i JOIN dela upita (sadržaj njenih atributa se ne koristi).
     * @param whereSection Deo SQL upita sa uslovom pretrage.
     * @return lista pronađenih entiteta koji zadovoljavaju uslov pretrage,
     * odnosno prazna lista ako nijedan zapis ne zadovoljava zadati uslov
     * @throws Exception Ako izvršavanje SELECT upita nad bazom podataka ne uspe.
     */
    @Override
    public List<GenericEntity> getByCriteria(GenericEntity entity, String whereSection) throws Exception {
        try {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        List<GenericEntity> list= new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getTableName()).append(" ").append(entity.getTableAlias()).append(" ")
                .append(entity.getJoinQuery()).append(" ").append(whereSection);
        String query = sb.toString();
        
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs =statement.executeQuery(query);
        while(rs.next()){
                list.add(entity.getEntityFromResultSet(rs));
            }
        rs.close();
        statement.close();
        return list;
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    
}
