/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.article;

import java.time.LocalDate;

/**
 *
 * @author houss
 */
public class Livre extends Article {
    protected String isbn;
    protected int nbPages;

    public Livre(String description, double prixInitial, 
            int nbExemplaires, String isbn, int nbPages) {
        super(description, prixInitial, nbExemplaires);
        this.isbn = isbn;
        this.nbPages = nbPages;
    }

    @Override
    public String getNumero() {
        return isbn;
    }

    @Override
    public double calculerPrix() {
        // Réduction de 50% au mois d'avril
        if (LocalDate.now().getMonthValue() == 4) {
            return prixInitial * 0.5;
        }
        return prixInitial;
    }

    @Override
    public String versFichier() {
        return isbn + System.lineSeparator() + 
               description + ":" + prixInitial + ":" + nbExemplaires + 
                ":" + nbPages;
    }

    @Override
    public String toString() {
        return String.format("Livre [%s] %s | Pages: %d", 
                isbn, super.toString(), nbPages);
    }
}
