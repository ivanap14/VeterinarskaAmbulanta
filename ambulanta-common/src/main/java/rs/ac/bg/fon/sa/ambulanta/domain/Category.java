/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.io.Serializable;

/**
 *Predstavlja kategoriju specijalizacije. 
 *
 *Uzima jednu od 5 mogućih vrednosti.
 *
 * @author Korisnik
 * @version 1.0
 */
public enum Category implements Serializable{
	/**
     * Opšta medicina.
     */
	OPSTA,
	/**
     * Klinička medicina.
     */
    KLINICKA,
    /**
     * Hirurgija.
     */
    HIRURSKA,
    /**
     * Anesteziologija.
     */
    ANESTEZIOLOSKA,
    /**
     * Dijagnostika i laboratorijske analize.
     */
    DIJAGNOSTICKA
}
