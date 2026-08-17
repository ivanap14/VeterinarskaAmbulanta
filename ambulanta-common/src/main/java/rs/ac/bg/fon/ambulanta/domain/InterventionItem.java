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
public class InterventionItem implements GenericEntity{
    private Intervention intervention;
    private int rb;
    private double price;
    private int quantity;
    private double amount;
    private Service service;

    public InterventionItem() {
    }

    public InterventionItem(Intervention intervention, int rb, double price, int quantity, 
            									double amount, Service service) {
		setIntervention(intervention);
		setRb(rb);
		setPrice(price);
		setQuantity(quantity);
		setAmount(amount);
		setService(service);
	}

    public Intervention getIntervention() {
        return intervention;
    }

    public int getRb() {
        return rb;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public Service getService() {
        return service;
    }

    public void setIntervention(Intervention intervention) {
        if (intervention == null) {
            throw new IllegalArgumentException("Intervencija mora biti uneta.");
        }
        this.intervention = intervention;
    }

    public void setRb(int rb) {
        if (rb <= 0) {
            throw new IllegalArgumentException("Redni broj mora biti pozitivan broj.");
        }
        this.rb = rb;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Cena mora biti pozitivan broj.");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Količina mora biti pozitivan broj.");
        }
        this.quantity = quantity;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Iznos ne može biti negativan.");
        }
        this.amount = amount;
    }

    public void setService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("Usluga mora biti uneta.");
        }
        this.service = service;
    }

    

    @Override
    public String toString() {
        return "rb=" + rb + ", price=" + price + ", quantity=" + quantity + ", amount=" + amount;
    }

    @Override
    public String getTableName() {
        return "interventionitem";
    }
    
    @Override
    public String getTableAlias() {
        return "item";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "idIntervention, rb, price, quantity, amount, idService";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(intervention.getId()).append(",")
                .append(rb).append(",")
                .append(price).append(",")
                .append(quantity).append(",")
                .append(amount).append(",")
                .append(service.getId());
     
        return sb.toString();
    }

    @Override
    public void setId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        Intervention intervention = new Intervention();
        intervention.setId(rs.getLong("idIntervention"));
        Service service = new Service();
        service.setName(rs.getString("s.name"));
        service.setId(rs.getLong("s.id"));
        return new InterventionItem(intervention, rs.getInt("item.rb"), rs.getDouble("item.price"), rs.getInt("item.quantity"),
                rs.getDouble("item.amount"), service);
    }

    @Override
    public String getJoinQuery() {
        return "INNER JOIN service s ON item.idService=s.id";
    }

    @Override
    public String setAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryCondition() {
        return "idIntervention=" + intervention.getId();
    }

}
