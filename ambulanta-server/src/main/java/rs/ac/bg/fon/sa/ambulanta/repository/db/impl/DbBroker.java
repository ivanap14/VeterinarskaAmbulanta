/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.repository.db.impl;

import rs.ac.bg.fon.ambulanta.domain.*;
import java.sql.Connection;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.repository.db.*;
import java.sql.*;
import java.util.ArrayList;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class DbBroker implements DbRepository<GenericEntity>{

    //Implementacija generiskih metoda DdBrokera

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
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
            
            return entity;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

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
