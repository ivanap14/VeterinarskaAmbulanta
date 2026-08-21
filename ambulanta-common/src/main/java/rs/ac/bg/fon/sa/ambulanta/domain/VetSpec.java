/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Predstavlja vezu između veterinara i specijalizacije koju je stekao.
 *
 * Sadrži podatke o veterinaru, specijalizaciji, datumu diplomiranja i
 * instituciji na kojoj je specijalizacija stečena.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */
public class VetSpec implements GenericEntity{
    /**
     * Veterinar koji je stekao specijalizaciju.
     */
	private Veterinarian veterinarian;
    
	/**
     * Specijalizacija koju je veterinar stekao.
     */
	private Specialization specialization;
    
	/**
     * Datum diplomiranja (sticanja specijalizacije).
     */
	private LocalDate graduationDate;
    
	/**
     * Institucija na kojoj je specijalizacija stečena.
     */
	private String institution;

    /**
     * Kreira objekat klase VetSpec sa null vrednostima atributa.
     */
    public VetSpec() {
    }

    /**
     * Kreira objekat klase VetSpec sa unetim vrednostima atributa.
     *
     * Poziva set metodu za svaki parametar uz logičku kontrolu za sve parametre.
     *
     * @param veterinarian Veterinar koji je stekao specijalizaciju. Ne sme biti null.
     * @param specialization Specijalizacija koju je veterinar stekao. Ne sme biti null.
     * @param graduationDate Datum diplomiranja. Ne sme biti null niti u budućnosti.
     * @param institution Institucija na kojoj je specijalizacija stečena. Ne sme biti null niti prazna. Ne sme imati više od 100 karaktera.
     */
    public VetSpec(Veterinarian veterinarian, Specialization specialization, 
            								LocalDate graduationDate, String institution) {
		setVeterinarian(veterinarian);
		setSpecialization(specialization);
		setGraduationDate(graduationDate);
		setInstitution(institution);
	}

    /**
     * Vraća veterinara koji je stekao specijalizaciju.
     *
     * @return veterinar
     */
    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    /**
    * Vraća specijalizaciju koju je veterinar stekao.
    *
    * @return specijalizacija
    */
    public Specialization getSpecialization() {
        return specialization;
    }

    /**
     * Vraća datum diplomiranja (sticanja specijalizacije).
     *
     * @return datum diplomiranja
     */
    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    /**
     * Vraća instituciju na kojoj je specijalizacija stečena.
     *
     * @return naziv institucije
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Postavlja veterinara na unetu vrednost.
     *
     * @param veterinarian Veterinar koji je stekao specijalizaciju.
     * @throws java.lang.NullPointerException Ako je unet veterinar null.
     */
    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new NullPointerException("Veterinar mora biti unet.");
        }
        this.veterinarian = veterinarian;
    }

    /**
     * Postavlja specijalizaciju na unetu vrednost.
     *
     * @param specialization Specijalizacija koju je veterinar stekao.
     * @throws java.lang.NullPointerException Ako je uneta specijalizacija null.
     */
    public void setSpecialization(Specialization specialization) {
        if (specialization == null) {
            throw new NullPointerException("Specijalizacija mora biti uneta.");
        }
        this.specialization = specialization;
    }

    /**
     * Postavlja datum diplomiranja na unetu vrednost.
     *
     * @param graduationDate Datum diplomiranja.
     * @throws java.lang.NullPointerException Ako je unet datum diplomiranja null.
     * @throws java.lang.IllegalArgumentException Ako je datum diplomiranja u budućnosti.
     */
    public void setGraduationDate(LocalDate graduationDate) {
        if (graduationDate == null) {
            throw new NullPointerException("Datum diplomiranja mora biti unet.");
        }
        if (graduationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datum diplomiranja ne može biti u budućnosti.");
        }
        this.graduationDate = graduationDate;
    }

    /**
     * Postavlja instituciju na unetu vrednost.
     *
     * @param institution Institucija na kojoj je specijalizacija stečena.
     * @throws java.lang.IllegalArgumentException Ako je uneta institucija null ili prazna. Ako je naziv institucije duži od 100 karaktera.
     */
    public void setInstitution(String institution) {
        if (institution == null || institution.isEmpty()) {
            throw new IllegalArgumentException("Institucija mora biti uneta.");
        }
        if (institution.length() > 100) {
            throw new IllegalArgumentException("Naziv institucije je predugačak.");
        }
        this.institution = institution;
    }

        @Override
    public String getTableName() {
        return "vetspec";
    }

    @Override
    public String getTableAlias() {
        return "";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "idVeterinarian, idSpecialization, graduationDate, institution";
    }

    @Override
    public String getInsertValues() {

        StringBuilder sb = new StringBuilder();

        sb.append(veterinarian.getId())
          .append(",")
          .append(specialization.getId())
          .append(",")
          .append("'").append(graduationDate).append("'")
          .append(",")
          .append("'").append(institution).append("'");

        return sb.toString();
    }


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setId(rs.getLong("idVeterinarian"));
        Specialization specialization = new Specialization();
        specialization.setId(rs.getLong("idSpecialization"));
        specialization.setName(rs.getString("name"));
        specialization.setCategory(Category.valueOf(rs.getString("category")));
        return new VetSpec(veterinarian, specialization, rs.getDate("graduationDate").toLocalDate(), rs.getString("institution"));
    }

    @Override
    public String getJoinQuery() {
        return "INNER JOIN veterinarian v ON v.id=idVeterinarian INNER JOIN specialization s ON s.id=idSpecialization";
    }

    @Override
    public String setAttributeValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryCondition() {
    	return "idVeterinarian=" + veterinarian.getId() + " AND idSpecialization=" + specialization.getId();
    }

    @Override
    public void setIdFromRS(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Generiše hash kod za objekat klase VetSpec.
     *
     * Hash kod se izračunava na osnovu specijalizacije i veterinara.
     *
     * @return celobrojna vrednost hash koda
     */
	@Override
	public int hashCode() {
		return Objects.hash(specialization, veterinarian);
	}

    /**
     * Poredi dva objekta klase VetSpec po veterinaru i specijalizaciji.
     *
     * @param obj Drugi objekat sa kojim se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase VetSpec imaju istog veterinara i istu specijalizaciju, ili su na istoj adresi</li>
     * <li><b>false</b> - ako je drugi objekat null, ako je druge klase ili
     * ako se veterinar ili specijalizacija razlikuju.</li>
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
		VetSpec other = (VetSpec) obj;
		return Objects.equals(specialization, other.specialization) && Objects.equals(veterinarian, other.veterinarian);
	}

    
    
    
    
}
