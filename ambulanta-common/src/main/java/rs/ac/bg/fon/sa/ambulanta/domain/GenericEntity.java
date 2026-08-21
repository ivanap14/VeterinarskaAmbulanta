/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.io.Serializable;

import java.sql.*;

import java.sql.*;

/**
 * Predstavlja opšti interfejs koji moraju da implementiraju sve domenske klase
 * (entiteti) kako bi nad njima mogle da se izvršavaju generičke CRUD operacije 
 * u bazi podataka putem DbBroker-a.
 *
 * <p>Interfejs sadrži metode neophodne za dinamičko generisanje SQL upita
 * (INSERT, UPDATE, DELETE, SELECT) i mapiranje rezultata iz baze podataka.</p>
 *
 *<p>Nasleđuje interfejs Serializable čime je omogućen prenos i serijalizacija
 * domenskih objekata kroz mrežu (u klijent-server arhitekturi pri mrežnoj komunikaciji).</p>
 *
 * @author Korisnik
 * @version 1.0
 */
public interface GenericEntity extends Serializable {

	/**
     * Vraća naziv tabele u bazi podataka koja odgovara domenskom entitetu.
     *
     * @return Naziv tabele kao String.
     */
    public String getTableName();
    
    /**
     * Vraća alijas tabele koji se koristi pri spajanju tabela u SQL upitima.
     *
     * @return Alijas tabele kao String.
     */
    public String getTableAlias();

    /**
     * Vraća spisak kolona tabele koje se popunjavaju prilikom unosa novog zapisa (INSERT).
     * <p><b>Napomena: </b>Primarni ključ se ne navodi ukoliko je auto-inkrement u bazi.</p>
     *
     * @return Nazivi kolona odvojeni zarezom.
     */
    public String getColumnNamesForInsert();
    
    /**
     * Vraća vrednosti atributa entiteta pripremljene za formiranje VALUES dela SQL INSERT upita.
     * <p><b>Napomena: </b>Tekstualne i enum vrednosti u vraćenom String-u moraju biti obuhvaćene jednostrukim navodnicima.</p>
     *
     * @return Formatirane vrednosti odvojene zarezom
     */
    public String getInsertValues();

    /**
     * Postavlja generisani primarni ključ (ID) iz baze podataka u objekat nakon uspešnog unosa.
     * <p><b>Napomena: </b>Metodu poziva DbBroker odmah po izvršavanju INSERT upita nad auto-increment kolonom.</p>
     *
     * @param id Generisani identifikator tipa Long.
     */
    public void setIdFromRS(Long id);

    /**
     * Kreira i vraća novu instancu domenskog entiteta na osnovu tekućeg reda iz ResultSet-a.
     * <p><b>Napomena: </b>Ukoliko entitet sadrži spoljni ključ, ovde se vrši i kompletno rekonstruisanje tog povezanog objekta.</p>
     *
     * @param rs Rezultat SQL upita pozicioniran na odgovarajući red.
     * @return Nova instanca domenske klase koja implementira GenericEntity.
     * @throws SQLException Ako dođe do greške prilikom čitanja vrednosti iz ResultSet-a.
     */
    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException;

    /**
     * Vraća JOIN klauzulu SQL upita u slučaju kada je entitet povezan sa drugim tabelama.
     * <p><b>Napomena: </b>Ukoliko entitet nema spoljne ključeve ili relacije, metoda treba da vrati prazan String ("").</p>
     *
     * @return SQL klauzula za spajanje tabela.
     */
    public String getJoinQuery();

    /**
     * Vraća izraz za SET klauzulu SQL UPDATE upita.
     * <p><b>Napomena: </b>Atributi se formatiraju u obliku naziv_kolone = vrednost, gde se primarni ključ izostavlja iz SET dela.</p>
     *
     * @return Parovi kolona i vrednosti odvojeni zarezima.
     */
    public String setAttributeValues();

    /**
     * Vraća uslov za WHERE klauzulu SQL upita koji jednoznačno identifikuje zapis u bazi.
     * <p><b>Napomena: </b>Povratni String ne treba da sadrži samu reč WHERE, već samo uslov.</p>
     *
     * @return SQL uslov identifikacije.
     */
    public String getQueryCondition();
    
    
}
