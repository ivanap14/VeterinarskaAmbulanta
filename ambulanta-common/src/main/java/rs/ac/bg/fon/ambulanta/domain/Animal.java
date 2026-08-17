/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import rs.ac.bg.fon.ambulanta.domain.Owner;

/**
 *
 * @author Korisnik
 */
public class Animal implements GenericEntity{
    private Long id;
    private String name;
    private Species species;
    private int yearOfBirth;
    private Gender gender;
    private Owner owner;

    public Animal() {
    }

    public Animal(Long id, String name, Species species, int yearOfBirth, 
                                                   Gender gender, Owner owner) {
        setId(id);
        setName(name);
        setSpecies(species);
        setYearOfBirth(yearOfBirth);
        setGender(gender);
        setOwner(owner);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Ime životinje mora biti uneto.");
        }
        if (!name.matches("\\p{L}+")) {
            throw new IllegalArgumentException("Nadimak sme da sadrzi samo slova.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Ime životinje je predugačko.");
        }
        this.name = name;
    }

    public void setSpecies(Species species) {
        if (species == null) {
            throw new IllegalArgumentException("Vrsta mora biti uneta.");
        }
        this.species = species;
    }

    public void setYearOfBirth(int yearOfBirth) {
        if (yearOfBirth < 0) {
            throw new IllegalArgumentException("Godina rođenja mora biti pozitivan broj.");
        }
        if (!String.valueOf(yearOfBirth).matches("0|\\d{4}")) {
            throw new IllegalArgumentException("Godište može biti 0 (ako je nepoznato) ili četvorocifren broj.");
        }
        if (yearOfBirth > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Godina rođenja ne može biti u budućnosti.");
        }
        this.yearOfBirth = yearOfBirth;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Pol mora biti unet.");
        }
        this.gender = gender;
    }

    public void setOwner(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Vlasnik mora biti unet.");
        }
        this.owner = owner;
    }

    @Override
    public String toString() {
        return ""+getId()+" "+getName()+" "+getSpecies();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);     
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String getTableName() {
        return "animal";
    }
    
    @Override
    public String getTableAlias() {
        return "a";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "name, species, yearOfBirth, gender, idOwner";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("'").append(",")
            .append("'").append(species).append("'").append(",")
            .append(yearOfBirth).append(",")
            .append("'").append(gender).append("'").append(",")
            .append(owner.getId());

    return sb.toString();
    }

    @Override
    public void setId(long id) {
        this.id=id;
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        Owner owner = new Owner(rs.getLong("id"), rs.getString("firstname"),rs.getString("lastname"), 
                rs.getString("jmbg"), rs.getBoolean("loyaltyCard"), rs.getString("phone"), 
                rs.getString("email"), rs.getString("address"));
        return new Animal(rs.getLong("id"),rs.getString("name"), Species.valueOf(rs.getString("species")),
                rs.getInt("yearOfBirth"), Gender.valueOf(rs.getString("gender")), owner);
    }

    @Override
    public String getJoinQuery() {
        return "INNER JOIN owner o ON a.idOwner=o.id";
    }

    @Override
    public String setAttributeValues() {
        return  "name='"+name+"',"+
                "species='"+species+"',"+
                "yearOfBirth="+yearOfBirth+","+
                "gender='"+gender+"',"+
                "idOwner="+owner.getId();
    }

    @Override
    public String getQueryCondition() {
        return "id="+getId();
    }

    
    
    
    
}
