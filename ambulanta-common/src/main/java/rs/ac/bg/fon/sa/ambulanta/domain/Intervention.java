/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja intervenciju izvršenu nad životinjom.
 *
 * Svaka intervencija ima id, datum, napomenu, popust po osnovu loyalty
 * kartice, popust po osnovu broja usluga, ukupan iznos bez popusta,
 * ukupan iznos sa popustom, veterinara, životinju i listu stavki intervencije.
 *
 * Klasa implementira interfejs GenericEntity, čime je omogućeno njeno
 * korišćenje u opštim generičkim operacijama nad bazom.
 *
 * @author Korisnik
 * @version 1.0
 */
public class Intervention implements GenericEntity{
	/**
     * Jedinstveni identifikator intervencije.
     */
	private Long id;
	/**
     * Datum kada je intervencija izvršena.
     */
    private LocalDate date;
    /**
     * Napomena vezana za intervenciju.
     */
    private String notes;
    /**
     * Procenat popusta u zavisnosti da li vlasnik ima loyalty karticu.
     */
    private int discountForLoyalty;
    /**
     * Procenat popusta po osnovu broja izvršenih usluga prilikom tekuće intervencije.
     */
    private int discountForNumberOfServices;
    /**
     * Ukupan iznos intervencije pre obračunavanja popusta.
     */
    private double totalAmountWithoutDiscount;
    /**
     * Ukupan iznos intervencije nakon obračunavanja popusta.
     */
    private double totalAmountWithDiscount;
    /**
     * Veterinar koji je izvršio intervenciju.
     */
    private Veterinarian veterinarian;
    /**
     * Životinja nad kojom je izvršena intervencija.
     */
    private Animal animal;
    /**
     * Lista stavki koje čine intervenciju.
     */
    private List<InterventionItem> interventionItems;

    /**
     * Kreira prazan objekat klase Intervention sa podrazumevanim vrednostima atributa.
     */
    public Intervention() {
    }
	
    /**
     * Kreira objekat klase Intervention sa unetim vrednostima atributa.
     *
     * Poziva set metodu za svaki parametar uz logičku kontrolu za
     * date, notes, discountForLoyalty, discountForNumberOfServices,
     * totalAmountWithoutDiscount, totalAmountWithDiscount, veterinarian,
     * animal i interventionItems.
     *
     * @param id Jedinstveni identifikator intervencije.
     * @param date Datum izvršenja intervencije. Ne sme biti null niti u budućnosti.
     * @param notes Napomena vezana za intervenciju. Može biti null, ali ne sme imati više od 255 karaktera.
     * @param discountForLoyalty Popust po osnovu loyalty kartice. Mora biti između 0 i 100.
     * @param discountForNumberOfServices Popust po osnovu broja usluga. Mora biti između 0 i 100.
     * @param totalAmountWithoutDiscount Ukupan iznos bez popusta. Ne sme biti negativan.
     * @param totalAmountWithDiscount Ukupan iznos sa popustom. Ne sme biti negativan.
     * @param veterinarian Veterinar koji je izvršio intervenciju. Ne sme biti null.
     * @param animal Životinja nad kojom je izvršena intervencija. Ne sme biti null.
     * @param interventionItems Lista stavki intervencije.
     */
	public Intervention(Long id, LocalDate date, String notes, int discountForLoyalty, int discountForNumberOfServices, 
	            double totalAmountWithoutDiscount, double totalAmountWithDiscount, 
	            Veterinarian veterinarian, Animal animal, List<InterventionItem> interventionItems) {
		setId(id);
		setDate(date);
		setNotes(notes);
		setDiscountForLoyalty(discountForLoyalty);
		setDiscountForNumberOfServices(discountForNumberOfServices);
		setTotalAmountWithoutDiscount(totalAmountWithoutDiscount);
		setTotalAmountWithDiscount(totalAmountWithDiscount);
		setVeterinarian(veterinarian);
		setAnimal(animal);
		setInterventionItems(interventionItems);
	}
    
	/**
	 * Vraća jedinstveni identifikator intervencije.
	 *
	 * @return ID intervencije
	 */
    public Long getId() {
        return id;
    }

    /**
     * Vraća datum izvršenja intervencije.
     *
     * @return datum intervencije
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Vraća napomenu vezanu za intervenciju.
     *
     * @return napomena
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Vraća procenat popusta po osnovu loyalty kartice.
     *
     * @return procenat popusta
     */
    public int getDiscountForLoyalty() {
        return discountForLoyalty;
    }

    /**
     * Vraća procenat popusta po osnovu broja usluga pruženih u tekućoj intervenciji.
     *
     * @return procenat popusta
     */
    public int getDiscountForNumberOfServices() {
        return discountForNumberOfServices;
    }

    /**
     * Vraća ukupan iznos intervencije pre obračunavanja popusta.
     *
     * @return ukupan iznos bez popusta
     */
    public double getTotalAmountWithoutDiscount() {
        return totalAmountWithoutDiscount;
    }

    /**
     * Vraća ukupan iznos intervencije nakon obračunavanja popusta.
     *
     * @return ukupan iznos sa popustom
     */
    public double getTotalAmountWithDiscount() {
        return totalAmountWithDiscount;
    }

    /**
     * Vraća veterinara koji je izvršio intervenciju.
     *
     * @return veterinar
     */
    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    /**
     * Vraća životinju nad kojom je izvršena intervencija.
     *
     * @return životinja
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Vraća listu stavki intervencije.
     *
     * @return lista stavki intervencije
     */
    public List<InterventionItem> getInterventionItems() {
        return interventionItems;
    }
    
    /**
     * Postavlja id intervencije na unetu vrednost.
     *
     * @param id Jedinstveni identifikator intervencije.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Postavlja datum intervencije.
     *
     * Datum ne sme biti null niti u budućnosti.
     *
     * @param date Datum intervencije.
     * @throws NullPointerException Ako je datum null.
     * @throws IllegalArgumentException Ako je datum u budućnosti.
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("Datum intervencije mora biti unet.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datum intervencije ne može biti u budućnosti.");
        }
        this.date = date;
    }

    /**
     * Postavlja napomenu intervencije.
     *
     * Napomena može biti null, ali ne sme imati više od 255 karaktera.
     *
     * @param notes Napomena intervencije.
     * @throws IllegalArgumentException Ako napomena ima više od 255 karaktera.
     */
    public void setNotes(String notes) {
    	 if (notes != null && notes.length() > 255) {
    	        throw new IllegalArgumentException("Napomena je predugačka.");
    	    }
        this.notes = notes;
    }

    /**
     * Postavlja popust po osnovu loyalty kartice.
     *
     * Vrednost mora biti u intervalu od 0 do 100.
     *
     * @param discountForLoyalty Procenat popusta.
     * @throws IllegalArgumentException Ako vrednost nije između 0 i 100.
     */
    public void setDiscountForLoyalty(int discountForLoyalty) {
        if (discountForLoyalty < 0 || discountForLoyalty > 100) {
            throw new IllegalArgumentException("Popust za loyalty karticu mora biti između 0 i 100.");
        }
        this.discountForLoyalty = discountForLoyalty;
    }

    /**
     * Postavlja popust po osnovu broja usluga.
     *
     * Vrednost mora biti u intervalu od 0 do 100.
     *
     * @param discountForNumberOfServices Procenat popusta.
     * @throws IllegalArgumentException Ako vrednost nije između 0 i 100.
     */
    public void setDiscountForNumberOfServices(int discountForNumberOfServices) {
        if (discountForNumberOfServices < 0 || discountForNumberOfServices > 100) {
            throw new IllegalArgumentException("Popust za broj usluga mora biti između 0 i 100.");
        }
        this.discountForNumberOfServices = discountForNumberOfServices;
    }

    /**
     * Postavlja ukupan iznos intervencije bez popusta.
     *
     * @param totalAmountWithoutDiscount Ukupan iznos bez popusta.
     * @throws IllegalArgumentException Ako je iznos negativan.
     */
    public void setTotalAmountWithoutDiscount(double totalAmountWithoutDiscount) {
        if (totalAmountWithoutDiscount < 0) {
            throw new IllegalArgumentException("Ukupan iznos bez popusta ne može biti negativan.");
        }
        this.totalAmountWithoutDiscount = totalAmountWithoutDiscount;
    }

    /**
     * Postavlja ukupan iznos intervencije sa popustom.
     *
     * @param totalAmountWithDiscount Ukupan iznos sa popustom.
     * @throws IllegalArgumentException Ako je iznos negativan.
     */
    public void setTotalAmountWithDiscount(double totalAmountWithDiscount) {
        if (totalAmountWithDiscount < 0) {
            throw new IllegalArgumentException("Ukupan iznos sa popustom ne može biti negativan.");
        }
        this.totalAmountWithDiscount = totalAmountWithDiscount;
    }

    /**
     * Postavlja veterinara koji je izvršio intervenciju.
     *
     * @param veterinarian Veterinar.
     * @throws NullPointerException Ako je veterinar null.
     */
    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new NullPointerException("Veterinar mora biti unet.");
        }
        this.veterinarian = veterinarian;
    }

    /**
     * Postavlja životinju nad kojom se vrši intervencija.
     *
     * @param animal Životinja.
     * @throws NullPointerException Ako je životinja null.
     */
    public void setAnimal(Animal animal) {
        if (animal == null) {
            throw new NullPointerException("Životinja mora biti uneta.");
        }
        this.animal = animal;
    }

    /**
     * Postavlja listu stavki intervencije.
     *
     * @param interventionItems Lista stavki intervencije.
     */
    public void setInterventionItems(List<InterventionItem> interventionItems) {
        this.interventionItems = interventionItems;
    }

    /**
     * Generiše hash kod za objekat klase Intervention.
     *
     * Hash kod se izračunava na osnovu jedinstvenog identifikatora intervencije.
     *
     * @return celobrojna vrednost hash koda
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Poredi dve intervencije po id-u
     * 
     * @param obj Druga intervencija sa kojom se poredi.
     * @return
     * <ul>
     * <li><b>true</b> - ako oba objekta klase Intervention imaju isti id ili su na istoj adresi</li>
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
        final Intervention other = (Intervention) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String getTableName() {
        return "intervention";
    }
    
    @Override
    public String getTableAlias() {
        return "i";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "date, notes, discountForLoyalty, discountForNumberOfServices, totalAmountWithoutDiscount, totalAmountWithDiscount, idVeterinarian, idAnimal";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(date).append("'").append(",")
                .append("'").append(notes).append("'").append(",")
                .append(discountForLoyalty).append(",")
                .append(discountForNumberOfServices).append(",")
                .append(totalAmountWithoutDiscount).append(",")
                .append(totalAmountWithDiscount).append(",")
                .append(veterinarian.getId()).append(",")
                .append(animal.getId());
     
        return sb.toString();
    }


    @Override
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException {
        Owner owner = new Owner(rs.getLong("idOwner"), rs.getString("o.firstname"), rs.getString("o.lastname"),
                rs.getString("o.jmbg"), rs.getBoolean("loyaltyCard"), rs.getString("o.phone"), 
                rs.getString("o.email"), rs.getString("o.address"));
        Animal animal = new Animal(rs.getLong("idAnimal"), rs.getString("a.name"), Species.valueOf(rs.getString("a.species")),
                rs.getInt("a.yearOfBirth"), Gender.valueOf(rs.getString("a.gender")), owner);
        Veterinarian veterinarian = new Veterinarian(rs.getLong("idVeterinarian"), rs.getString("v.firstname"), rs.getString("v.lastname"), 
                rs.getDate("v.birthday").toLocalDate(), rs.getString("v.phone"), rs.getString("v.email"), null);
        List<InterventionItem> items = new ArrayList<>();
        return new Intervention(rs.getLong("i.id"), rs.getDate("i.date").toLocalDate(), rs.getString("i.notes"), 
        		rs.getInt("i.discountForLoyalty"), rs.getInt("i.discountForNumberOfServices"), rs.getDouble("i.totalAmountWithoutDiscount"), 
        		rs.getDouble("i.totalAmountWithDiscount"), veterinarian, animal, items);
    }

    @Override
    public String getJoinQuery() {
        return "INNER JOIN veterinarian v ON i.idVeterinarian=v.id INNER JOIN animal a ON i.idAnimal=a.id INNER JOIN owner o ON a.idOwner=o.id";
    }

    @Override
    public String setAttributeValues() {
        return  "date='"+date+"',"+
                "notes='"+notes+"',"+
                "discountForLoyalty="+discountForLoyalty+","+
                "discountForNumberOfServices="+discountForNumberOfServices+","+
                "totalAmountWithoutDiscount="+totalAmountWithoutDiscount+","+
                "totalAmountWithDiscount="+totalAmountWithDiscount+","+
                "idVeterinarian="+veterinarian.getId()+","+
                "idAnimal="+animal.getId();
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
