/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ambulanta.domain;

import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author Korisnik
 */
public class VetSpec implements GenericEntity{
    private Veterinarian veterinarian;
    private Specialization specialization;
    private LocalDate graduationDate;
    private String institution;

    public VetSpec() {
    }

    public VetSpec(Veterinarian veterinarian, Specialization specialization, 
            								LocalDate graduationDate, String institution) {
		setVeterinarian(veterinarian);
		setSpecialization(specialization);
		setGraduationDate(graduationDate);
		setInstitution(institution);
	}

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public String getInstitution() {
        return institution;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new IllegalArgumentException("Veterinar mora biti unet.");
        }
        this.veterinarian = veterinarian;
    }

    public void setSpecialization(Specialization specialization) {
        if (specialization == null) {
            throw new IllegalArgumentException("Specijalizacija mora biti uneta.");
        }
        this.specialization = specialization;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        if (graduationDate == null) {
            throw new IllegalArgumentException("Datum diplomiranja mora biti unet.");
        }
        if (graduationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datum diplomiranja ne može biti u budućnosti.");
        }
        this.graduationDate = graduationDate;
    }

    public void setInstitution(String institution) {
        if (institution == null || institution.isEmpty()) {
            throw new IllegalArgumentException("Institucija mora biti uneta.");
        }
        if (institution.length() > 100) {
            throw new IllegalArgumentException("Naziv institucije je predugačak.");
        }
        this.institution = institution;
    }

    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String getTableAlias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getJoinQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
