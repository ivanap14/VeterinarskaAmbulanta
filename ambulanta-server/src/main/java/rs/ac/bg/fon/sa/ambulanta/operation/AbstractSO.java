/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.operation;

import rs.ac.bg.fon.sa.ambulanta.repository.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.*;

/**
 * Predstavlja apstraktnu osnovu za sve sistemske operacije u aplikaciji.
 *
 * Implementira Template Method patern kroz metodu execute(Object),
 * koja definiše fiksni redosled koraka izvršavanja svake sistemske operacije:
 * proveru preduslova, uspostavljanje transakcije, izvršavanje same operacije
 * i potvrđivanje transakcije, uz automatsko poništavanje transakcije u
 * slučaju greške.
 *
 * Svaka konkretna sistemska operacija nasleđuje ovu klasu i implementira
 * metode preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 *
 * @author Korisnik
 * @version 1.0
 */

public abstract class AbstractSO {
    
	/**
     * Repozitorijum preko kog se izvršavaju operacije nad bazom podataka.
     */
    protected final Repository repository;

    /**
     * Kreira objekat klase AbstractSO i inicijalizuje repozitorijum na
     * konkretnu implementaciju DbBroker.
     */
    public AbstractSO() {
        this.repository = new DbBroker();
    }
    
    /**
     * Izvršava sistemsku operaciju po fiksnom redosledu koraka: proverava
     * preduslove, uspostavlja transakciju, izvršava operaciju i potvrđuje
     * transakciju.
     *
     * Ukoliko bilo koji od koraka izazove izuzetak, transakcija se
     * poništava (rollback) i izuzetak se prosleđuje pozivaocu.
     *
     * @param param Parametar potreban za izvršavanje operacije.
     * @throws Exception Ako proveravanje preduslova, uspostavljanje transakcije ili izvršavanje operacije ne uspe.
     */
    public void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
            System.out.println("Uspesno izvrsena operacija!!!");
        } catch (Exception exception) {
            System.out.println("Neuspesno izvrsena operacija!!!");
            rollbackTransaction();
            throw exception;
        }
    }

    /**
     * Proverava da li su ispunjeni preduslovi neophodni za izvršavanje
     * konkretne sistemske operacije.
     *
     * Svaka konkretna sistemska operacija implementira sopstvenu poslovnu
     * logiku provere.
     *
     * @param param Parametar nad kojim se proveravaju preduslovi.
     * @throws Exception Ako preduslovi za izvršavanje operacije nisu ispunjeni.
     */
    protected abstract void preconditions(Object param) throws Exception;

    /**
     * Uspostavlja konekciju sa bazom podataka pre izvršavanja operacije.
     *
     * @throws Exception Ako uspostavljanje konekcije ne uspe.
     */
    private void startTransaction() throws Exception {
        ((DbRepository)repository).connect();
    }

    /**
     * Izvršava konkretnu poslovnu logiku sistemske operacije nad
     * repozitorijumom.
     *
     * Svaka konkretna sistemska operacija implementira ovu metodu i u njoj
     * poziva odgovarajuću operaciju repozitorijuma (dodavanje, izmena,
     * brisanje ili čitanje).
     *
     * @param param Parametar nad kojim se izvršava operacija.
     * @throws Exception Ako izvršavanje operacije ne uspe.
     */
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Potvrđuje (commit) sve izmene izvršene tokom operacije.
     *
     * @throws Exception Ako potvrđivanje transakcije ne uspe.
     */
    private void commitTransaction() throws Exception {
        ((DbRepository)repository).commit();
        }

    /**
     * Poništava (rollback) sve izmene izvršene tokom operacije, u slučaju
     * da je došlo do greške.
     *
     * @throws Exception Ako poništavanje transakcije ne uspe.
     */
    private void rollbackTransaction() throws Exception {
        ((DbRepository)repository).rollback();
    }

    /**
     * Zatvara konekciju sa bazom podataka.
     *
     * @throws Exception Ako zatvaranje konekcije ne uspe.
     */
    private void disconnect() throws Exception {
        ((DbRepository)repository).disconnect();
    }
}
