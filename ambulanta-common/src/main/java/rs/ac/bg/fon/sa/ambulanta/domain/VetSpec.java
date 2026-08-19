/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            throw new NullPointerException("Veterinar mora biti unet.");
        }
        this.veterinarian = veterinarian;
    }

    public void setSpecialization(Specialization specialization) {
        if (specialization == null) {
            throw new NullPointerException("Specijalizacija mora biti uneta.");
        }
        this.specialization = specialization;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        if (graduationDate == null) {
            throw new NullPointerException("Datum diplomiranja mora biti unet.");
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
        return "vetspec";
    }

    @Override
    public String getTableAlias() {
        return "";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "idVeterinarian, idSpecialization, graduationDate, institution";
    }

    @Override
    public String getInsertValues() {

        StringBuilder sb = new StringBuilder();

        sb.append(veterinarian.getId())
          .append(",")
          .append(specialization.getId())
          .append(",")
          .append("'").append(graduationDate).append("'")
          .append(",")
          .append("'").append(institution).append("'");

        return sb.toString();
    }


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setId(rs.getLong("idVeterinarian"));
        Specialization specialization = new Specialization();
        specialization.setId(rs.getLong("idSpecialization"));
        specialization.setName(rs.getString("name"));
        specialization.setCategory(Category.valueOf(rs.getString("category")));
        return new VetSpec(veterinarian, specialization, rs.getDate("graduationDate").toLocalDate(), rs.getString("institution"));
    }

    @Override
    public String getJoinQuery() {
        return "INNER JOIN veterinarian v ON v.id=idVeterinarian INNER JOIN specialization s ON s.id=idSpecialization";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
