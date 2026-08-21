/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.vetspec;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 * Klasa zadužena za dodavanje nove veze između veterinara i
 * specijalizacije (VetSpec) u bazu podataka.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 * Pre dodavanja proverava da li veterinar već poseduje unetu specijalizaciju.
 *
 * @author Korisnik
 * @version 1.0
 */
public class AddNewVetSpec extends AbstractSO{

	/**
     * Veza veterinar-specijalizacija koja je dodata nakon uspešnog
     * izvršavanja operacije.
     */
    private VetSpec vetSpec;

    /**
     * Proverava da li je prosleđen parametar, da li predstavlja instancu
     * klase VetSpec i da li veterinar već poseduje prosleđenu specijalizaciju.
     *
     * @param param Veza veterinar-specijalizacija koja se dodaje. Ne sme
     * biti null.
     * @throws Exception Ako je parametar null, nije instanca klase VetSpec
     * ili veterinar već ima unetu prosleđenu specijalizaciju.
     */
    @Override
    protected void preconditions(Object param) throws Exception {

        if (param == null || !(param instanceof VetSpec)) {
            throw new Exception("Objekat pogresnog tipa.");
        }

        vetSpec = (VetSpec) param;

        checkExistVetSpec(vetSpec);
    }

    /**
     * Dodaje prosleđenu vezu veterinar-specijalizacija u bazu podataka i
     * čuva je kao rezultat operacije.
     *
     * @param param Veza veterinar-specijalizacija koja se dodaje.
     * @throws Exception Ako dodavanje veze u bazu podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {

        vetSpec = (VetSpec) repository.add((VetSpec) param);
    }

    /**
     * Vraća vezu veterinar-specijalizacija dodatu nakon izvršavanja operacije.
     *
     * @return dodata veza veterinar-specijalizacija
     */
    public VetSpec getVetSpec() {
        return vetSpec;
    }

    /**
     * Proverava da li veterinar iz prosleđene veze već poseduje datu
     * specijalizaciju u bazi podataka.
     *
     * @param vetSpec Veza veterinar-specijalizacija čije se postojanje
     * proverava.
     * @throws Exception Ako veterinar već ima unetu prosleđenu specijalizaciju.
     */
    private void checkExistVetSpec(VetSpec vetSpec) throws Exception {

        List<VetSpec> list = repository.getByCriteria(new VetSpec(),
                "WHERE idVeterinarian=" + vetSpec.getVeterinarian().getId()
                + " AND idSpecialization=" + vetSpec.getSpecialization().getId());

        if (!list.isEmpty()) {
            throw new Exception("Veterinar već ima unetu ovu specijalizaciju.");
        }
    }
    
}
