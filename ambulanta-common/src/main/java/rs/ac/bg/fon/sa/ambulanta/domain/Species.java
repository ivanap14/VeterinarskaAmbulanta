/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.io.Serializable;

/**
 *
 *Predstavlja biološku vrstu životinje.
 *
 *Služi za klasifikaciju životinja koje se leče u veterinarskoj ambulanti.
 *Uzima jednu od 10 mogućih vrednosti.
 *
 * @author Korisnik
 * @version 1.0
 */
public enum Species implements Serializable{
	/** Sve rase pasa. */
    PAS,

    /** Sve vrste mačaka. */
    MACKA,

    /** Zečevi. */
    ZEC,

    /** Glodari (hrčak, veverica, morsko prase, miš, ...). */
    GLODAR,

    /** Ptice (papagaj, kanarinac, golub, ...). */
    PTICA,

    /** Ribe. */
    RIBA,

    /** Vodozemci (žaba, daždevnjak, ...). */
    VODOZEMAC ,

    /** Reptili (kornjača, zmija, kameleon, krokodil, ...). */
    REPTIL,

    /** Domaće farmske životinje. */
    DOMACA,

    /** Ostale nestandardne i egzotične vrste. */
    OSTALO
}
