/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import rs.ac.bg.fon.sa.ambulanta.domain.Owner;

/**
 *Predstavlja životinju nad kojom se vrši intervencija.
 *
 *Svaka životinja ima id, nadimak, vrstu, godinu rođenja, pol, vlasnika.
 *
 *Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 * 
 * @author Korisnik
 * @version 1.0
 */
public class Animal implements GenericEntity{
	/**
     * Jedinstveni identifikator životinje.
     */
    private Long id;
    /**
     * Nadimak životinje.
     */
    private String name;
    /**
     * Biološka vrsta životinje.
     */
    private Species species;
    /**
     * Godina rođenja životinje.
     */
    private int yearOfBirth;
    /**
     * Pol životinje.
     */
    private Gender gender;
    /**
     * Vlasnik životinje.
     */
    private Owner owner;

    /**
     * Kreira objekat klase Animal (novu životinju) sa null vrednostima atributa.  
     */
    public Animal() {
    }

    /**
     * Kreira objekat klase Animal (novu životinju) sa unetim vrednostima atributa.  
     * 
     * Poziva set metodu za svaki parametar uz logičku kontrolu za parametre name, species, yearOfBirth, gender, owner
     *
     *@param id Jedinstveni identifikator životinje.
     *@param name Nadimak životinje. Ne sme biti null niti prazno. Ne sme da sadrži brojeve. Ne sme da ima više od 50 karaktera.
     *@param species Biološka vrsta životinje. Ne sme biti null.
     *@param yearOfBirth Godina rođenja životinje. Moguće biti 0-nepoznato ili četvorocifren broj. Ne sme biti negativan broj niti godina u budućnoszi.
     *@param gender Pol životinje. Ne sme biti null.
     *@param owner Vlasnik životinje. Ne sme biti null.
     */
    public Animal(Long id, String name, Species species, int yearOfBirth, 
                                                   Gender gender, Owner owner) {
        setId(id);
        setName(name);
        setSpecies(species);
        setYearOfBirth(yearOfBirth);
        setGender(gender);
        setOwner(owner);
    }

    /**
    * Vraća jedinstveni identifikator životinje.
    * 
    * @return ID životinje
    */
    public Long getId() {
        return id;
    }

    /**
     * Vraća nadimak životinje.
     * 
     * @return nadimak životinje
     */
    public String getName() {
        return name;
    }

    /**
     * Vraća biološku vrstu životinje.
     * 
     * @return vrsta životinje
     */
    public Species getSpecies() {
        return species;
    }

    /**
     * Vraća godinu rođenja životinje.
     * 
     * @return godina rođenja
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Vraća pol životinje.
     * 
     * @return pol životinje
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Vraća vlasnika životinje.
     * 
     * @return vlasnik životinje
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Postavlja id životinje na unetu vrednost.
     * 
     * @param id Jedinstveni identifikator životinje.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Postavlja nadimak životinje na unetu vrednost.
     * 
     * @param name Nadimak životinje.
     * @throws java.lang.IllegalArgumentException Ako je unet nadimak null ili prazan. Ako nadimak sadrži broj. Ako je nadimak duži od 50 karaktera.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Ime životinje mora biti uneto.");
        }
        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Nadimak ne sme da sadrži brojeve.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Ime životinje je predugačko.");
        }
        this.name = name;
    }

    /**
     * Postavlja vrstu životinje na unetu vrednost.
     * 
     * @param species Vrsta životinje.
     * @throws java.lang.NullPointerException Ako je uneta vrsta null.
     */
    public void setSpecies(Species species) {
        if (species == null) {
            throw new NullPointerException("Vrsta mora biti uneta.");
        }
        this.species = species;
    }

    /**
     * Postavlja godinu rođenja životinje na unetu vrednost.
     * 
     * @param yearOfBirth Godina rođenja životinje.
     * @throws java.lang.IllegalArgumentException Ako je uneta godina rođenja negativna.
     * Ako je uneta godina rođenja nije 0 ili četvorocifren broj. Ako je uneta godina rođenja u budućnosti.
     */
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

    /**
     * Postavlja pol životinje na unetu vrednost.
     * 
     * @param gender Pol životinje.
     * @throws java.lang.NullPointerException Ako je unet pol null.
     * 
     */
    public void setGender(Gender gender) {
        if (gender == null) {
            throw new NullPointerException("Pol mora biti unet.");
        }
        this.gender = gender;
    }

    /**
     * Postavlja vlasnika životinje na unetu vrednost.
     * 
     * @param owner Vlasnik životinje.
     * @throws java.lang.NullPointerException Ako je unet vlasnik null.
     * 
     */
    public void setOwner(Owner owner) {
        if (owner == null) {
            throw new NullPointerException("Vlasnik mora biti unet.");
        }
        this.owner = owner;
    }

    /**
     * Vraća String sa id-em, nadimkom i vrstom životinje.
     * 
     * @return osnovni podaci o životinji u formatu "[id] nadimak (vrsta)"
     * 
     */
    @Override
    public String toString() {
    	return "["+getId()+"] "+getName()+" ("+getSpecies()+")";
    }

    /**
     * Generiše hash kod za objekat klase Animal.
     * 
     * Hash kod se izračunava  na osnovu jedinstvenog identifikatora (id).
     *
     * @return celobrojna vrednost hash koda
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);     
        return hash;
    }

    /**
     * Poredi dve životinje po id-u
     * 
     * @param obj Druga životinja sa kojom se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase Animal imaju isti id ili su na istoj adresi</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase ili
	 * ako objekti nemaju isti id.</li>
     * </ul>
     */
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

    @Override
    public void setIdFromRS(Long id) {
        this.id=id;
    }
    
    
    
}
