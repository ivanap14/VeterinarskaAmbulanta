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
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.loyaltyCard = loyaltyCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public void setFirstname(String firstname) throws Exception {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) throws Exception {
        this.lastname = lastname;
    }

    public void setJmbg(String jmbg) throws Exception {
        this.jmbg = jmbg;
    }
    
    public void setLoyaltyCard(Boolean loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    public void setPhone(String phone) throws Exception {
        this.phone = phone;
    }

    public void setEmail(String email) throws Exception {
        this.email = email;
    }

    public void setAddress(String address) throws Exception {
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
    public void setId(long id) {
        this.id=id;
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

}
