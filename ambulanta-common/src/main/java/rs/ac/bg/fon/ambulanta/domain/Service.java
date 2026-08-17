/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class Service implements GenericEntity{
    private Long id;
    private String name;
    private double price;
    private String description;

    public Service() {
    }

    public Service(Long id, String name, double price, String description) {
        setId(id);
        setName(name);
        setPrice(price);
        setDescription(description);
    }
    

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Naziv usluge mora biti unet.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Naziv usluge je predugačak.");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Cena mora biti pozitivan broj.");
        }
        this.price = price;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Opis mora biti unet.");
        }
        if (description.length() > 255) {
            throw new IllegalArgumentException("Opis je predugačak.");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getTableName() {
        return "service";
    }
    
    @Override
    public String getTableAlias() {
        return "";
    }
    

    @Override
    public String getColumnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setId(long id) {
        this.id=id;
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Service(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("description"));
    }

    @Override
    public String getJoinQuery() {
        return "";
    }

    @Override
    public String setAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
