/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja jednu stavku intervencije.
 *
 * Svaka stavka intervencije ima intervenciju kojoj pripada, redni broj,
 * cenu usluge, količinu, ukupan iznos i uslugu.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */
public class InterventionItem implements GenericEntity{
	
	/**
     * Intervencija kojoj stavka pripada.
     */
	private Intervention intervention;
	
	/**
     * Redni broj stavke u okviru intervencije.
     */
	private int rb;
    
	/**
     * Cena usluge.
     */
	private double price;
    
	/**
     * Količina usluge.
     */
	private int quantity;
    
	/**
     * Ukupan iznos stavke.
     */
	private double amount;
    
	/**
     * Usluga koja predstavlja stavku intervencije.
     */
	private Service service;

	/**
     * Kreira prazan objekat klase InterventionItem sa podrazumevanim vrednostima atributa.
     */
    public InterventionItem() {
    }

    /**
     * Kreira objekat klase InterventionItem sa unetim vrednostima atributa.
     *
     * Poziva set metode za svaki parametar uz logičku kontrolu.
     *
     * @param intervention Intervencija kojoj stavka pripada. Ne sme biti null.
     * @param rb Redni broj stavke. Mora biti pozitivan.
     * @param price Cena usluge. Mora biti pozitivan broj.
     * @param quantity Količina usluge. Mora biti pozitivan broj.
     * @param amount Ukupan iznos stavke. Ne sme biti negativan.
     * @param service Usluga koja predstavlja stavku. Ne sme biti null.
     */
    public InterventionItem(Intervention intervention, int rb, double price, int quantity, 
            									double amount, Service service) {
		setIntervention(intervention);
		setRb(rb);
		setPrice(price);
		setQuantity(quantity);
		setAmount(amount);
		setService(service);
	}

    /**
     * Vraća intervenciju kojoj stavka pripada.
     *
     * @return intervencija
     */
    public Intervention getIntervention() {
        return intervention;
    }

    /**
     * Vraća redni broj stavke.
     *
     * @return redni broj
     */
    public int getRb() {
        return rb;
    }

    /**
     * Vraća cenu usluge.
     *
     * @return cena
     */
    public double getPrice() {
        return price;
    }

    /**
     * Vraća količinu usluge.
     *
     * @return količina
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Vraća ukupan iznos stavke.
     *
     * @return iznos
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Vraća uslugu koja predstavlja stavku.
     *
     * @return usluga
     */
    public Service getService() {
        return service;
    }

    /**
     * Postavlja intervenciju kojoj stavka pripada.
     *
     * @param intervention Intervencija.
     * @throws NullPointerException Ako je intervencija null.
     */
    public void setIntervention(Intervention intervention) {
        if (intervention == null) {
            throw new NullPointerException("Intervencija mora biti uneta.");
        }
        this.intervention = intervention;
    }

    /**
     * Postavlja redni broj stavke.
     *
     * @param rb Redni broj stavke. Mora biti pozitivan broj.
     * @throws IllegalArgumentException Ako redni broj nije pozitivan.
     */
    public void setRb(int rb) {
        if (rb <= 0) {
            throw new IllegalArgumentException("Redni broj mora biti pozitivan broj.");
        }
        this.rb = rb;
    }

    /**
     * Postavlja cenu usluge.
     *
     * @param price Cena usluge. Mora biti pozitivan broj.
     * @throws IllegalArgumentException Ako cena nije pozitivna.
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Cena mora biti pozitivan broj.");
        }
        this.price = price;
    }

    /**
     * Postavlja količinu usluge.
     *
     * @param quantity Količina usluge. Mora biti pozitivan broj.
     * @throws IllegalArgumentException Ako količina nije pozitivna.
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Količina mora biti pozitivan broj.");
        }
        this.quantity = quantity;
    }

    /**
     * Postavlja ukupan iznos stavke.
     *
     * @param amount Ukupan iznos stavke.
     * @throws IllegalArgumentException Ako je iznos negativan.
     */
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Iznos ne može biti negativan.");
        }
        this.amount = amount;
    }

    /**
     * Postavlja uslugu koja predstavlja stavku intervencije.
     *
     * @param service Usluga.
     * @throws NullPointerException Ako je usluga null.
     */
    public void setService(Service service) {
        if (service == null) {
            throw new NullPointerException("Usluga mora biti uneta.");
        }
        this.service = service;
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

    @Override
    public void setIdFromRS(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
