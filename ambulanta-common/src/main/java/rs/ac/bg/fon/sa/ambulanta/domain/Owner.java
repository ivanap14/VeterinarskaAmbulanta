/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Predstavlja vlasnika životinje.
 *
 * Svaki vlasnik ima id, ime, prezime, JMBG, podatak o loyalty kartici,
 * broj telefona, email adresu i adresu stanovanja.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */
public class Owner implements GenericEntity{
	 /**
     * Jedinstveni identifikator vlasnika.
     */
	private Long id;
	
	/**
     * Ime vlasnika.
     */
	private String firstname;
    
	/**
     * Prezime vlasnika.
     */
	private String lastname;
	/**
     * Jedinstveni matični broj vlasnika.
     */
	private String jmbg;
    
	/**
     * Podatak da li vlasnik poseduje loyalty karticu.
     */
	private Boolean loyaltyCard;
    
	/**
     * Broj telefona vlasnika.
     */
	private String phone;
    
	/**
     * Email adresa vlasnika.
     */
	private String email;
    
	/**
     * Adresa stanovanja vlasnika.
     */
	private String address;

	/**
     * Kreira prazan objekat klase Owner sa podrazumevanim vrednostima atributa.
     */
    public Owner() {
    }

    /**
     * Kreira objekat klase Owner sa unetim vrednostima atributa.
     *
     * Poziva set metodu za svaki parametar uz logičku kontrolu.
     *
     * @param id Jedinstveni identifikator vlasnika.
     * @param firstname Ime vlasnika. Ne sme biti null, prazno niti duže od 50 karaktera. Sme da sadrži samo slova.
     * @param lastname Prezime vlasnika. Ne sme biti null, prazno niti duže od 50 karaktera. Sme da sadrži samo slova.
     * @param jmbg JMBG vlasnika. Mora imati tačno 13 cifara.
     * @param loyaltyCard Podatak da li vlasnik poseduje loyalty karticu. Ne sme biti null.
     * @param phone Broj telefona. Mora imati 9 ili 10 cifara.
     * @param email Email adresa vlasnika. Mora sadržati znak @ i ne sme biti duža od 60 karaktera.
     * @param address Adresa vlasnika. Ne sme biti null, prazna niti duža od 100 karaktera.
     */
    public Owner(Long id, String firstname, String lastname, String jmbg, Boolean loyaltyCard,
            String phone, String email, String address) {
		setId(id);
		setFirstname(firstname);
		setLastname(lastname);
		setJmbg(jmbg);
		setLoyaltyCard(loyaltyCard);
		setPhone(phone);
		setEmail(email);
		setAddress(address);
	}
    
    /**
     * Vraća jedinstven identifikator vlasnika.
     *
     * @return jedinstven identifikator vlasnika
     */
    public Long getId() {
        return id;
    }

    /**
     * Vraća ime vlasnika.
     *
     * @return ime vlasnika
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Vraća prezime vlasnika.
     *
     * @return prezime vlasnika
     */
    public String getLastname() {
        return lastname;
    }
    
    /**
     * Vraća jedinstven matični broj vlasnika.
     *
     * @return jedinstven matični broj vlasnika
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Vraća informaciju da li vlasnik poseduje loyalty karticu.
     *
     * @return
     *<ul>
     * <li><b>true</b> - ako vlasnik poseduje loyalty karticu</li>
     * <li><b>false</b> - ako vlasnik ne poseduje loyalty karticu</li>
     *</ul>
     */
    public Boolean getLoyaltyCard() {
        return loyaltyCard;
    }

    /**
     * Vraća broj telefona vlasnika.
     *
     * @return broj telefona vlasnika
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Vraća email adresu vlasnika.
     *
     * @return email adresa vlasnika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Vraća adresu stanovanja vlasnika.
     *
     * @return adresa stanovanja vlasnika
     */
    public String getAddress() {
        return address;
    }

    /**
     * Postavlja id vlasnika na unetu vrednost.
     * 
     * @param id Jedinstveni identifikator vlasnika.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Postavlja ime vlasnika.
     *
     * Ime mora biti uneto, ne sme imati više od 50 karaktera i može sadržati
     * samo slova.
     *
     * @param firstname Ime vlasnika.
     * @throws IllegalArgumentException Ako je uneto ime null ili prazno. Ako je ime duže od 50 karaktera. Ako ime sadrži karakter koji nije slovo.
     */
    public void setFirstname(String firstname) {
        if (firstname == null || firstname.isEmpty()) {
            throw new IllegalArgumentException("Ime mora biti uneto.");
        }
        if (firstname.length() > 50) {
            throw new IllegalArgumentException("Ime je predugačko.");
        }
        if (!firstname.matches("\\p{L}+")) {
            throw new IllegalArgumentException("Ime sme da sadrži samo slova.");
        }
        this.firstname = firstname;
    }

    /**
     * Postavlja prezime vlasnika na unetu vrednost.
     *
     * Prezime mora biti uneto, ne sme imati više od 50 karaktera i može sadržati
     * samo slova.
     *
     * @param lastname Prezime vlasnika.
     * @throws java.lang.IllegalArgumentException Ako je uneto prezime null ili prazno. Ako je prezime duže od 50 karaktera. Ako prezime sadrži karakter koji nije slovo.
     */

    public void setLastname(String lastname) {
        if (lastname == null || lastname.isEmpty()) {
            throw new IllegalArgumentException("Prezime mora biti uneto.");
        }
        if (lastname.length() > 50) {
            throw new IllegalArgumentException("Prezime je predugačko.");
        }
        if (!lastname.matches("\\p{L}+")) {
            throw new IllegalArgumentException("Prezime sme da sadrži samo slova.");
        }
        this.lastname = lastname;
    }

    /**
     * Postavlja JMBG vlasnika na unetu vrednost.
     *
     * JMBG mora biti unet i mora imati tačno 13 cifara.
     *
     * @param jmbg Jedinstveni matični broj vlasnika.
     * @throws java.lang.IllegalArgumentException Ako je unet JMBG null ili prazan. Ako JMBG nema tačno 13 karaktera. Ako JMBG sadrži karakter koji nije cifra.
     */

    public void setJmbg(String jmbg) {
        if (jmbg == null || jmbg.isEmpty()) {
            throw new IllegalArgumentException("JMBG mora biti unet.");
        }
        if (jmbg.length() != 13) {
            throw new IllegalArgumentException("JMBG mora imati tačno 13 cifara.");
        }
        if (!jmbg.matches("\\d+")) {
            throw new IllegalArgumentException("JMBG sme da sadrži samo cifre.");
        }
        this.jmbg = jmbg;
    }

    /**
     * Postavlja podatak o loyalty kartici na unetu vrednost.
     *
     * @param loyaltyCard Podatak da li vlasnik poseduje loyalty karticu.
     * @throws java.lang.NullPointerException Ako je unet podatak o loyalty kartici null.
     */

    public void setLoyaltyCard(Boolean loyaltyCard) {
        if (loyaltyCard == null) {
            throw new NullPointerException("Podatak o loyalty kartici mora biti unet.");
        }
        this.loyaltyCard = loyaltyCard;
    }

    /**
     * Postavlja broj telefona vlasnika na unetu vrednost.
     *
     * Telefon mora biti unet i mora imati 9 ili 10 cifara.
     *
     * @param phone Broj telefona vlasnika.
     * @throws java.lang.IllegalArgumentException Ako je unet telefon null ili prazan. Ako telefon nema 9 ili 10 karaktera. Ako telefon sadrži karakter koji nije cifra.
     */

    public void setPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Telefon mora biti unet.");
        }
        if (phone.length() < 9 || phone.length() > 10) {
            throw new IllegalArgumentException("Telefon mora imati 9 ili 10 cifara");
        }
        if (!phone.matches("\\d+")) {
            throw new IllegalArgumentException("Telefon sme da sadrži samo cifre.");
        }
        this.phone = phone;
    }

    /**
     * Postavlja email adresu vlasnika na unetu vrednost.
     *
     * Email mora biti unet, mora sadržati znak @ i ne sme biti duži od 60 karaktera.
     *
     * @param email Email adresa vlasnika.
     * @throws java.lang.IllegalArgumentException Ako je unet email null ili prazan. Ako email ne sadrži znak @. Ako je email duži od 60 karaktera.
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email mora biti unet.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email nije u ispravnom formatu.");
        }
        if (email.length() > 60) {
            throw new IllegalArgumentException("Email je predugačak.");
        }
        this.email = email;
    }

    /**
     * Postavlja adresu stanovanja vlasnika na unetu vrednost.
     *
     * Adresa mora biti uneta i ne sme biti duža od 100 karaktera.
     *
     * @param address Adresa stanovanja vlasnika.
     * @throws java.lang.IllegalArgumentException Ako je uneta adresa null ili prazna. Ako je adresa duža od 100 karaktera.
     */

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Adresa mora biti uneta.");
        }
        if (address.length() > 100) {
            throw new IllegalArgumentException("Adresa je predugačka.");
        }
        this.address = address;
    }

    /**
     * Vraća String sa JMBG-om, imenom i prezimenom vlasnika.
     *
     * @return osnovni podaci o vlasniku u formatu "[jmbg] ime prezime"
     */

    @Override
    public String toString() {
        return "["+getJmbg()+"] "+getFirstname()+" "+getLastname();
    }

    @Override
    public String getTableName() {
        return "owner";
    }

    @Override
    public String getTableAlias() {
        return "o";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "firstname, lastname, jmbg, loyaltyCard, phone, email, address";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(firstname).append("'").append(",")
                .append("'").append(lastname).append("'").append(",")
                .append("'").append(jmbg).append("'").append(",")
                .append(loyaltyCard).append(",")
                .append("'").append(phone).append("'").append(",")
                .append("'").append(email).append("'").append(",")
                .append("'").append(address).append("'");
     
        return sb.toString();
    }


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Owner(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                rs.getString("jmbg"), rs.getBoolean("loyaltyCard"), rs.getString("phone"),
                rs.getString("email"), rs.getString("address"));
    }

    @Override
    public String getJoinQuery() {
        return "";
    }

    @Override
    public String setAttributeValues() {
        return  "firstname='"+firstname+"',"+
                "lastname='"+lastname+"',"+
                "jmbg='"+jmbg+"',"+
                "loyaltyCard="+loyaltyCard+","+
                "phone='"+phone+"',"+
                "email='"+email+"',"+
                "address='"+address+"'";
    }

    @Override
    public String getQueryCondition() {
        return "id="+getId();
    }

    @Override
    public void setIdFromRS(Long id) {
        this.id=id;
    }

    /**
     * Generiše hash kod za objekat klase Owner.
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
     * Poredi dva vlasnika po id-u.
     *
     * @param obj Drugi vlasnik sa kojim se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase Owner imaju isti id ili su na istoj adresi</li>
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
		Owner other = (Owner) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
