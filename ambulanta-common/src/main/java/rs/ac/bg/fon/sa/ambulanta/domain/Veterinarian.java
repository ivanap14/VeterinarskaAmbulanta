/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Predstavlja veterinara koji radi u ambulanti.
 *
 * Svaki veterinar ima id, ime, prezime, datum rođenja, broj telefona,
 * email adresu i šifru za prijavu.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */
public class Veterinarian implements GenericEntity{
	/**
     * Jedinstveni identifikator veterinara.
     */
	private Long id;
    
	/**
     * Ime veterinara.
     */
	private String firstname;
    
	/**
     * Prezime veterinara.
     */
	private String lastname;
    
	/**
     * Datum rođenja veterinara.
     */
	private LocalDate birthday;
    
	/**
     * Broj telefona veterinara.
     */
	private String phone;
    
	/**
     * Email adresa veterinara.
     */
	private String email;
    
	/**
     * Šifra veterinara za prijavu.
     */
	private String password;

	/**
     * Kreira objekat klase Veterinarian (novog veterinara) sa null vrednostima atributa.
     */
    public Veterinarian() {
    }

    /**
     * Kreira objekat klase Veterinarian (novog veterinara) sa unetim vrednostima atributa.
     *
     * Poziva set metodu za svaki parametar uz logičku kontrolu za parametre firstname, lastname,
     * birthday, phone, email, password.
     *
     * @param id Jedinstveni identifikator veterinara.
     * @param firstname Ime veterinara. Ne sme biti null niti prazno. Ne sme imati više od 50 karaktera. Sme da sadrži samo slova.
     * @param lastname Prezime veterinara. Ne sme biti null niti prazno. Ne sme imati više od 50 karaktera. Sme da sadrži samo slova.
     * @param birthday Datum rođenja veterinara. Ne sme biti null niti u budućnosti.
     * @param phone Broj telefona veterinara. Mora imati 9 ili 10 cifara.
     * @param email Email adresa veterinara. Mora sadržati znak @ i ne sme biti duža od 60 karaktera.
     * @param password Šifra veterinara. Ako je uneta, mora imati između 8 i 60 karaktera.
     */
    public Veterinarian(Long id, String firstname, String lastname, LocalDate birthday, String phone, String email, String password) {
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
        setBirthday(birthday);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
    }
    
    /**
    * Kreira objekat klase Veterinarian sa unetim email-om i šifrom, namenjen prijavi veterinara.
    *
    * Poziva set metodu za svaki parametar uz logičku kontrolu.
    *
    * @param email Email adresa veterinara. Mora sadržati znak @ i ne sme biti duža od 60 karaktera.
    * @param password Šifra veterinara. Ako je uneta, mora imati između 8 i 60 karaktera.
    */
    public Veterinarian(String email, String password) {
          setEmail(email);
          setPassword(password);
    }

    /**
     * Vraća jedinstveni identifikator veterinara.
     *
     * @return ID veterinara
     */
    public Long getId() {
        return id;
    }

    /**
     * Vraća ime veterinara.
     *
     * @return ime veterinara
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Vraća prezime veterinara.
     *
     * @return prezime veterinara
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Vraća datum rođenja veterinara.
     *
     * @return datum rođenja veterinara
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Vraća broj telefona veterinara.
     *
     * @return broj telefona veterinara
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Vraća email adresu veterinara.
     *
     * @return email adresa veterinara
     */
    public String getEmail() {
        return email;
    }

    /**
     * Vraća šifru veterinara.
     *
     * @return šifra veterinara
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja id veterinara na unetu vrednost.
     *
     * @param id Jedinstveni identifikator veterinara.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Postavlja ime veterinara na unetu vrednost.
     *
     * @param firstname Ime veterinara.
     * @throws java.lang.IllegalArgumentException Ako je uneto ime null ili prazno. Ako je ime duže od 50 karaktera. Ako ime sadrži karakter koji nije slovo.
     */
    public void setFirstname(String firstname) {
    	if (firstname == null || firstname.isEmpty()) {
            throw new IllegalArgumentException("Ime mora biti uneto.");
        }
    	if (firstname.length() > 50) {
            throw new IllegalArgumentException("Ime je predugacko.");
        }
    	if (!firstname.matches("\\p{L}+")) {
            throw new IllegalArgumentException("Ime sme da sadrzi samo slova.");
        }
        this.firstname = firstname;
    }

    /**
     * Postavlja prezime veterinara na unetu vrednost.
     *
     * @param lastname Prezime veterinara.
     * @throws java.lang.IllegalArgumentException Ako je uneto prezime null ili prazno. Ako je prezime duže od 50 karaktera. Ako prezime sadrži karakter koji nije slovo.
     */
    public void setLastname(String lastname) {
    	if (lastname == null || lastname.isEmpty()) {
            throw new IllegalArgumentException("Prezime mora biti uneto.");
        }
        if (lastname.length() > 50) {
            throw new IllegalArgumentException("Prezime je predugacko.");
        }
        if (!lastname.matches("\\p{L}+")) {
            throw new IllegalArgumentException("Prezime sme da sadrzi samo slova.");
        }
        this.lastname = lastname;
    }

    /**
     * Postavlja datum rođenja veterinara na unetu vrednost.
     *
     * @param birthday Datum rođenja veterinara.
     * @throws java.lang.IllegalArgumentException Ako je unet datum rođenja null. Ako je datum rođenja u budućnosti.
     */
    public void setBirthday(LocalDate birthday) {
    	if (birthday == null) {
            throw new IllegalArgumentException("Datum rodjenja mora biti unet.");
        }
        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datum rodjenja ne moze biti u buducnosti.");
        }
        this.birthday = birthday;
    }

    /**
     * Postavlja broj telefona veterinara na unetu vrednost.
     *
     * @param phone Broj telefona veterinara.
     * @throws java.lang.IllegalArgumentException Ako je unet telefon null ili prazan. Ako telefon nema 9 ili 10 karaktera. Ako telefon sadrži karakter koji nije cifra.
     */
    public void setPhone(String phone) {
    	if(phone == null || phone.isEmpty()){
            throw new IllegalArgumentException("Nije unet telefon.");
        }
    	if(phone.length() < 9 || phone.length() > 10) {
    		throw new IllegalArgumentException("Telefon mora imati 9 ili 10 cifara");
    	}
    	if(!phone.matches("\\d+")) {
    		throw new IllegalArgumentException("Telefon sme da sadrži samo cifre.");
    	}
        this.phone = phone;
    }

    /**
     * Postavlja email adresu veterinara na unetu vrednost.
     *
     * @param email Email adresa veterinara.
     * @throws java.lang.IllegalArgumentException Ako je unet email null ili prazan. Ako email ne sadrži znak @. Ako je email duži od 60 karaktera.
     */
    public void setEmail(String email)  {
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Nije unet email.");
        }
        if(!email.contains("@")) {
        	throw new IllegalArgumentException("Email nije u ispravnom formatu.");
        }
        if(email.length()>60) {
        	throw new IllegalArgumentException("Email je predugačak.");
        }
        this.email = email;
    }

    /**
     * Postavlja šifru veterinara na unetu vrednost.
     *
     * @param password Šifra veterinara.
     * @throws java.lang.IllegalArgumentException Ako uneta šifra nije null i ima manje od 8 ili više od 60 karaktera.
     */
    public void setPassword(String password)  {
    	if (password!=null && (password.length() < 8 || password.length() > 60)) {
            throw new IllegalArgumentException("Sifra mora imati izmedju 8 i 60 karaktera.");
        }
        this.password = password;
    }

    /**
     * Vraća String sa id-em, imenom i prezimenom veterinara.
     *
     * @return osnovni podaci o veterinaru u formatu "[id] ime prezime"
     */
    @Override
    public String toString() {
        return "["+getId()+"] "+getFirstname()+" "+getLastname();
    }

    /**
     * Generiše hash kod za objekat klase Veterinarian.
     *
     * Hash kod se izračunava na osnovu jedinstvenog identifikatora (id).
     *
     * @return celobrojna vrednost hash koda
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Poredi dva veterinara po id-u.
     *
     * @param obj Drugi veterinar sa kojim se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase Veterinarian imaju isti id ili su na istoj adresi</li>
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
        final Veterinarian other = (Veterinarian) obj;
        return Objects.equals(this.id, other.id);
    }
    
    

    @Override
    public String getTableName() {
        return "veterinarian";
    }

    @Override
    public String getTableAlias() {
        return "";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "firstname, lastname, birthday, phone, email, password";
    }


    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(firstname).append("',")
          .append("'").append(lastname).append("',")
          .append("'").append(birthday).append("',")
          .append("'").append(phone).append("',")
          .append("'").append(email).append("',")
          .append("'").append(password).append("'");
        return sb.toString();
    }



    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException{
            return new Veterinarian(rs.getLong("id"), rs.getString("firstname"),rs.getString("lastname") ,
                                 rs.getDate("birthday").toLocalDate() ,rs.getString("phone") 
                                    ,rs.getString("email") ,rs.getString("password"));
    }

    @Override
    public String getJoinQuery() {
        return "";
    }

    @Override
    public String setAttributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("firstname='").append(firstname).append("',")
          .append("lastname='").append(lastname).append("',")
          .append("birthday='").append(birthday).append("',")
          .append("phone='").append(phone).append("',")
          .append("email='").append(email).append("',")
          .append("password='").append(password).append("'");
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

    
}
