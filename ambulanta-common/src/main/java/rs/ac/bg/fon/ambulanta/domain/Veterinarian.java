/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Veterinarian implements GenericEntity{
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String password;

    public Veterinarian() {
    }

    public Veterinarian(Long id, String firstname, String lastname, LocalDate birthday, String phone, 
                                                                            String email, String password) {
        this.id = id;
        setFirstname(firstname);
        setLastname(lastname);
        setBirthday(birthday);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
    }
    
    public Veterinarian(Long id, String firstname, String lastname, LocalDate birthday, String phone, String email) {
        this.id = id;
        setFirstname(firstname);
        setLastname(lastname);
        setBirthday(birthday);
        setPhone(phone);
        setEmail(email);
    }

    public Veterinarian(String email, String password) {
          setEmail(email);
          setPassword(password);
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setBirthday(LocalDate birthday) {
    	if (birthday == null) {
            throw new IllegalArgumentException("Datum rodjenja mora biti unet.");
        }
        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datum rodjenja ne moze biti u buducnosti.");
        }
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
    	if(phone == null || phone.isEmpty()){
            throw new IllegalArgumentException("Telefon mora biti unet.");
        }
    	if(phone.length() < 9 || phone.length() > 10) {
    		throw new IllegalArgumentException("Telefon mora imati 9 ili 10 cifara");
    	}
    	if(!phone.matches("\\d+")) {
    		throw new IllegalArgumentException("Telefon sme da sadrži samo cifre.");
    	}
        this.phone = phone;
    }

    public void setEmail(String email)  {
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email mora biti unet.");
        }
        if(!email.contains("@")) {
        	throw new IllegalArgumentException("Email nije u ispravnom formatu.");
        }
        if(email.length()>60) {
        	throw new IllegalArgumentException("Email je predugačak.");
        }
        this.email = email;
    }

    public void setPassword(String password)  {
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Sifra mora biti uneta.");
        }
        
        if (password.length() < 8 || password.length() > 60) {
            throw new IllegalArgumentException("Sifra mora imati izmedju 8 i 60 karaktera.");
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return ""+getId()+" "+getFirstname()+" "+getLastname();
    }

    @Override
    public String getTableName() {
        return "veterinarian";
    }

    @Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veterinarian other = (Veterinarian) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

	@Override
    public String getTableAlias() {
        return "";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
