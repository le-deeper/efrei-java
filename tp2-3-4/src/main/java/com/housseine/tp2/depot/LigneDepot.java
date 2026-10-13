/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.depot;

/**
 *
 * @author houss
 */
public class LigneDepot {
    protected String numero; // ISBN ou ISSN
    protected int nbExemplaires;

    public LigneDepot(String numero, int nbExemplaires) {
        this.numero = numero;
        this.nbExemplaires = nbExemplaires;
    }

    public String getNumero() { return numero; }
    public int getNbExemplaires() { return nbExemplaires; }

    public String versFichier() {
        return nbExemplaires + ":" + numero;
    }

    @Override
    public String toString() {
        return String.format("- Réf: %s, Qté: %d", numero, nbExemplaires);
    }
}