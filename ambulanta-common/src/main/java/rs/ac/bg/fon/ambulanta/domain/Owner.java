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
public class Owner implements GenericEntity{
    private Long id;
    private String firstname;
    private String lastname;
    private String jmbg;
    private Boolean loyaltyCard;
    private String phone;
    private String email;
    private String address;

    public Owner() {
    }

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
    
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    
    public String getJmbg() {
        return jmbg;
    }

    public Boolean getLoyaltyCard() {
        return loyaltyCard;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setLoyaltyCard(Boolean loyaltyCard) {
        if (loyaltyCard == null) {
            throw new NullPointerException("Podatak o loyalty kartici mora biti unet.");
        }
        this.loyaltyCard = loyaltyCard;
    }

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

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Adresa mora biti uneta.");
        }
        if (address.length() > 100) {
            throw new IllegalArgumentException("Adresa je predugačka.");
        }
        this.address = address;
    }

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
}
