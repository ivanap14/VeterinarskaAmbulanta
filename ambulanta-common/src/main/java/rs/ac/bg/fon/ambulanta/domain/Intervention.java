/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Intervention implements GenericEntity{
    private Long id;
    private LocalDate date;
    private String notes;
    private int discountForLoyalty;
    private int discountForNumberOfServices;
    private double totalAmountWithoutDiscount;
    private double totalAmountWithDiscount;
    private Veterinarian veterinarian;
    private Animal animal;
    private List<InterventionItem> interventionItems;

    public Intervention() {
    }

    public Intervention(Long id, LocalDate date, String notes, int discountForLoyalty, 
                                    int discountForNumberOfServices, double totalAmountWithoutDiscount, 
                                    double totalAmountWithDiscount, Veterinarian veterinarian, Animal animal) {
        this.id = id;
        this.date = date;
        this.notes = notes;
        this.discountForLoyalty = discountForLoyalty;
        this.discountForNumberOfServices = discountForNumberOfServices;
        this.totalAmountWithoutDiscount = totalAmountWithoutDiscount;
        this.totalAmountWithDiscount = totalAmountWithDiscount;
        this.veterinarian = veterinarian;
        this.animal = animal;
    }
    
    public Intervention(LocalDate date, String notes, int discountForLoyalty, int discountForNumberOfServices, double totalAmountWithoutDiscount, double totalAmountWithDiscount, Veterinarian veterinarian, Animal animal, List<InterventionItem> interventionItems) {
        this.date = date;
        this.notes = notes;
        this.discountForLoyalty = discountForLoyalty;
        this.discountForNumberOfServices = discountForNumberOfServices;
        this.totalAmountWithoutDiscount = totalAmountWithoutDiscount;
        this.totalAmountWithDiscount = totalAmountWithDiscount;
        this.veterinarian = veterinarian;
        this.animal = animal;
        this.interventionItems = interventionItems;
    }
        
    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public int getDiscountForLoyalty() {
        return discountForLoyalty;
    }

    public int getDiscountForNumberOfServices() {
        return discountForNumberOfServices;
    }

    public double getTotalAmountWithoutDiscount() {
        return totalAmountWithoutDiscount;
    }

    public double getTotalAmountWithDiscount() {
        return totalAmountWithDiscount;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public Animal getAnimal() {
        return animal;
    }

    public List<InterventionItem> getInterventionItems() {
        return interventionItems;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDiscountForLoyalty(int discountForLoyalty) {
        this.discountForLoyalty = discountForLoyalty;
    }

    public void setDiscountForNumberOfServices(int discountForNumberOfServices) {
        this.discountForNumberOfServices = discountForNumberOfServices;
    }

    public void setTotalAmountWithoutDiscount(double totalAmountWithoutDiscount) {
        this.totalAmountWithoutDiscount = totalAmountWithoutDiscount;
    }

    public void setTotalAmountWithDiscount(double totalAmountWithDiscount) {
        this.totalAmountWithDiscount = totalAmountWithDiscount;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setInterventionItems(List<InterventionItem> interventionItems) throws Exception {
        this.interventionItems = interventionItems;
    }

    @Override
    public String toString() {
        return ""+getId()+" "+getDate();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Intervention other = (Intervention) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String getTableName() {
        return "intervention";
    }
    
    @Override
    public String getTableAlias() {
        return "i";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "date, notes, discountForLoyalty, discountForNumberOfServices, totalAmountWithoutDiscount, totalAmountWithDiscount, idVeterinarian, idAnimal";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(date).append("'").append(",")
                .append("'").append(notes).append("'").append(",")
                .append(discountForLoyalty).append(",")
                .append(discountForNumberOfServices).append(",")
                .append(totalAmountWithoutDiscount).append(",")
                .append(totalAmountWithDiscount).append(",")
                .append(veterinarian.getId()).append(",")
                .append(animal.getId());
     
        return sb.toString();
    }

    @Override
    public void setId(long id) {
        this.id=id;
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        Owner owner = new Owner(rs.getLong("idOwner"), rs.getString("o.firstname"), rs.getString("o.lastname"),
                rs.getString("o.jmbg"), rs.getBoolean("loyaltyCard"), rs.getString("o.phone"), 
                rs.getString("o.email"), rs.getString("o.address"));
        Animal animal = new Animal(rs.getLong("idAnimal"), rs.getString("a.name"), Species.valueOf(rs.getString("a.species")),
                rs.getInt("a.yearOfBirth"), Gender.valueOf(rs.getString("a.gender")), owner);
        Veterinarian veterinarian = new Veterinarian(rs.getLong("idVeterinarian"), rs.getString("v.firstname"), rs.getString("v.lastname"), 
                rs.getDate("v.birthday").toLocalDate(), rs.getString("v.phone"), rs.getString("v.email"));
        return new Intervention(rs.getLong("i.id"), rs.getDate("i.date").toLocalDate(), rs.getString("i.notes"), rs.getInt("i.discountForLoyalty"), rs.getInt("i.discountForNumberOfServices"), rs.getDouble("i.totalAmountWithoutDiscount"), rs.getDouble("i.totalAmountWithDiscount"), veterinarian, animal);
    }

    @Override
    public String getJoinQuery() {
        return "INNER JOIN veterinarian v ON i.idVeterinarian=v.id INNER JOIN animal a ON i.idAnimal=a.id INNER JOIN owner o ON a.idOwner=o.id";
    }

    @Override
    public String setAttributeValues() {
        return  "date='"+date+"',"+
                "notes='"+notes+"',"+
                "discountForLoyalty="+discountForLoyalty+","+
                "discountForNumberOfServices="+discountForNumberOfServices+","+
                "totalAmountWithoutDiscount="+totalAmountWithoutDiscount+","+
                "totalAmountWithDiscount="+totalAmountWithDiscount+","+
                "idVeterinarian="+veterinarian.getId()+","+
                "idAnimal="+animal.getId();
    }

    @Override
    public String getQueryCondition() {
        return "id="+getId();
    }
    
}
