/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Predstavlja specijalizaciju koju veterinar može steći.
 *
 * Svaka specijalizacija ima id, naziv, kategoriju i opis.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */
public class Specialization implements GenericEntity{
    /**
     * Jedinstveni identifikator specijalizacije.
     */
	private Long id;
    
	/**
     * Naziv specijalizacije.
     */
	private String name;
    
    /**
     * Kategorija kojoj specijalizacija pripada.
     */
	private Category category;

    /**
     * Opis specijalizacije.
     */
	private String description;

    /**
     * Kreira objekat klase Specialization (novu specijalizaciju) sa null vrednostima atributa.
     */
    public Specialization() {
    }

    /**
     * Kreira objekat klase Specialization (novu specijalizaciju) sa unetim vrednostima atributa.
     *
     * Poziva set metodu za svaki parametar uz logičku kontrolu za parametre name, category, description.
     *
     * @param id Jedinstveni identifikator specijalizacije.
     * @param name Naziv specijalizacije. Ne sme biti null niti prazan. Ne sme imati više od 50 karaktera.
     * @param category Kategorija specijalizacije. Ne sme biti null.
     * @param description Opis specijalizacije. Ne sme biti null niti prazan. Ne sme imati više od 255 karaktera.
     */
    public Specialization(Long id, String name, Category category, String description) {
        setId(id);
        setName(name);
        setCategory(category);
        setDescription(description);
    }
    
    /**
     * Vraća jedinstveni identifikator specijalizacije.
     *
     * @return ID specijalizacije
     */
    public Long getId() {
        return id;
    }

    /**
     * Vraća naziv specijalizacije.
     *
     * @return naziv specijalizacije
     */
    public String getName() {
        return name;
    }
    
    /**
    * Vraća kategoriju specijalizacije.
    *
    * @return kategorija specijalizacije
    */
    public Category getCategory() {
        return category;
    }

    /**
     * Vraća opis specijalizacije.
     *
     * @return opis specijalizacije
     */
    public String getDescription() {
        return description;
    }

    /**
     * Postavlja id specijalizacije na unetu vrednost.
     *
     * @param id Jedinstveni identifikator specijalizacije.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Postavlja naziv specijalizacije na unetu vrednost.
     *
     * @param name Naziv specijalizacije.
     * @throws java.lang.IllegalArgumentException Ako je unet naziv null ili prazan. Ako je naziv duži od 50 karaktera.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Naziv specijalizacije mora biti unet.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Naziv specijalizacije je predugačak.");
        }
        this.name = name;
    }

    /**
     * Postavlja kategoriju specijalizacije na unetu vrednost.
     *
     * @param category Kategorija specijalizacije.
     * @throws java.lang.NullPointerException Ako je uneta kategorija null.
     */
    public void setCategory(Category category) {
        if (category == null) {
            throw new NullPointerException("Kategorija mora biti uneta.");
        }
        this.category = category;
    }

    /**
     * Postavlja opis specijalizacije na unetu vrednost.
     *
     * @param description Opis specijalizacije.
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
     * Vraća naziv specijalizacije.
     *
     * @return naziv specijalizacije
     */
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getQueryCondition() {
        return "id=" + getId();
    }

    @Override
    public void setIdFromRS(Long id) {
        this.id=id;
    }

    /**
     * Generiše hash kod za objekat klase Specialization.
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
     * Poredi dve specijalizacije po id-u.
     *
     * @param obj Druga specijalizacija sa kojom se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase Specialization imaju isti id ili su na istoj adresi</li>
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
		Specialization other = (Specialization) obj;
		return Objects.equals(id, other.id);
	}
    
}
