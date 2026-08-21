/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.vetspec;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;


/**
 * Klasa zadužena za preuzimanje svih specijalizacija (veza
 * veterinar-specijalizacija) koje poseduje prosleđeni veterinar.
 *
 * Nasleđuje apstraktnu klasu AbstractSO i implementira metode
 * preconditions(Object) i executeOperation(Object),
 * definišući na taj način sopstvena specifična poslovna pravila i logiku.
 *
 * @author Korisnik
 * @version 1.0
 */
public class GetVetSpecsByVeterinarian extends AbstractSO{

	/**
     * Lista veza veterinar-specijalizacija preuzeta nakon uspešnog
     * izvršavanja operacije.
     */
    List<VetSpec> vetSpecs;
    
    /**
     * Ne vrši eksplicitnu proveru preduslova; parametar se koristi direktno
     * prilikom izvršavanja operacije.
     *
     * @param param Veterinar za kog se pretražuju specijalizacije.
     * @throws Exception Nikada se ne baca.
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    /**
     * Preuzima sve veze veterinar-specijalizacija za veterinara sa
     * identifikatorom prosleđenog veterinara i čuva ih kao rezultat operacije.
     *
     * @param param Veterinar za kog se pretražuju specijalizacije.
     * @throws Exception Ako preuzimanje veza veterinar-specijalizacija iz
     * baze podataka ne uspe.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Veterinarian vet = (Veterinarian)param;
        String whereSection = "WHERE idVeterinarian="+vet.getId();
        vetSpecs = repository.getByCriteria(new VetSpec(), whereSection);
    }

    /**
     * Vraća listu specijalizacija (veza veterinar-specijalizacija) koje
     * poseduje prosleđeni veterinar. Ukoliko veterinar nema unetu nijednu
     * specijalizaciju, vraća praznu listu.
     *
     * @return lista specijalizacija veterinara, odnosno prazna lista ako
     * veterinar nema unetu nijednu specijalizaciju
     */
    public List<VetSpec> getVetSpecs() {
        return vetSpecs;
    }
    
    
    
}
