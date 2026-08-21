/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Predstavlja uslugu koju ambulanta pruža.
 *
 * Svaka usluga ima id, naziv, cenu i opis.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */

public class Service implements GenericEntity{
    /**
     * Jedinstveni identifikator usluge.
     */
	private Long id;
	
	/**
     * Naziv usluge.
     */
    private String name;
    
    /**
     * Cena usluge.
     */
    private double price;
    
    /**
     * Opis usluge.
     */
    private String description;

    /**
     * Kreira objekat klase Service (novu uslugu) sa null vrednostima atributa.
     */
    public Service() {
    }

    /**
     * Kreira objekat klase Service (novu uslugu) sa unetim vrednostima atributa.
     *
     * Poziva set metodu za svaki parametar uz logičku kontrolu za parametre name, price, description.
     *
     * @param id Jedinstveni identifikator usluge.
     * @param name Naziv usluge. Ne sme biti null niti prazan. Ne sme imati više od 50 karaktera.
     * @param price Cena usluge. Mora biti pozitivan broj.
     * @param description Opis usluge. Ne sme biti null niti prazan. Ne sme imati više od 255 karaktera.
     */
    public Service(Long id, String name, double price, String description) {
        setId(id);
        setName(name);
        setPrice(price);
        setDescription(description);
    }
    

    /**
     * Vraća jedinstveni identifikator usluge.
     *
     * @return ID usluge
     */
    public Long getId() {
        return id;
    }

    /**
     * Vraća naziv usluge.
     *
     * @return naziv usluge
     */
    public String getName() {
        return name;
    }

    /**
     * Vraća cenu usluge.
     *
     * @return cena usluge
     */
    public double getPrice() {
        return price;
    }

    /**
     * Vraća opis usluge.
     *
     * @return opis usluge
     */
    public String getDescription() {
        return description;
    }

    /**
     * Postavlja id usluge na unetu vrednost.
     *
     * @param id Jedinstveni identifikator usluge.
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Postavlja naziv usluge na unetu vrednost.
     *
     * @param name Naziv usluge.
     * @throws java.lang.IllegalArgumentException Ako je unet naziv null ili prazan. Ako je naziv duži od 50 karaktera.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Naziv usluge mora biti unet.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Naziv usluge je predugačak.");
        }
        this.name = name;
    }

    /**
     * Postavlja cenu usluge na unetu vrednost.
     *
     * @param price Cena usluge.
     * @throws java.lang.IllegalArgumentException Ako je uneta cena manja ili jednaka nuli.
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Cena mora biti pozitivan broj.");
        }
        this.price = price;
    }

    /**
     * Postavlja opis usluge na unetu vrednost.
     *
     * @param description Opis usluge.
     * @throws java.lang.IllegalArgumentException Ako je unet opis null ili prazan. Ako je opis duži od 255 karaktera.
     */
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Opis mora biti unet.");
        }
        if (description.length() > 255) {
            throw new IllegalArgumentException("Opis je predugačak.");
        }
        this.description = description;
    }

    /**
     * Vraća naziv usluge.
     *
     * @return naziv usluge
     */
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
    	return "name, price, description";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("',")
          .append(price).append(",")
          .append("'").append(description).append("'");
        return sb.toString();
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
        StringBuilder sb = new StringBuilder();
        sb.append("name='").append(name).append("',")
          .append("price=").append(price).append(",")
          .append("description='").append(description).append("'");
        return sb.toString();
    }

    @Override
    public String getQueryCondition() {
        return "id=" + id;
    }

    @Override
    public void setIdFromRS(Long id) {
        this.id = id;
    }

    /**
     * Generiše hash kod za objekat klase Service.
     *
     * Hash kod se izračunava na osnovu jedinstvenog identifikatora (id).
     *
     * @return celobrojna vrednost hash koda
     */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

    /**
     * Poredi dve usluge po id-u.
     *
     * @param obj Druga usluga sa kojom se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase Service imaju isti id ili su na istoj adresi</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase ili
     * ako objekti nemaju isti id.</li>
     * </ul>
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
