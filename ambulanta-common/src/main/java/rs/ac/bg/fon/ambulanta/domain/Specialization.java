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
public class Specialization implements GenericEntity{
    private Long id;
    private String name;
    private Category category;
    private String description;

    public Specialization() {
    }

    public Specialization(Long id, String name, Category category, String description) {
        setId(id);
        setName(name);
        setCategory(category);
        setDescription(description);
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Naziv specijalizacije mora biti unet.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Naziv specijalizacije je predugačak.");
        }
        this.name = name;
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new NullPointerException("Kategorija mora biti uneta.");
        }
        this.category = category;
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
        return "specialization";
    }
    
    @Override
    public String getTableAlias() {
        return "";
    }

    @Override
    public String getColumnNamesForInsert() {
       return "name, category, description";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("'").append(",")
            .append("'").append(category).append("'").append(",")
            .append("'").append(description).append("'");

    return sb.toString();
    }


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Specialization(rs.getLong("id"),rs.getString("name"), Category.valueOf(rs.getString("category")), rs.getString("description"));
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

    @Override
    public void setIdFromRS(Long id) {
        this.id=id;
    }
    
}
