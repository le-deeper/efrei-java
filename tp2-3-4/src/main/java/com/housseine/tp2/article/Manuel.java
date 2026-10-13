/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.article;

/**
 *
 * @author houss
 */
public class Manuel extends Livre {
    protected String matiere;
    protected String niveauScolaire;

    public Manuel(String description, double prixInitial, int nbExemplaires, 
            String isbn, int nbPages, String matiere, String niveauScolaire) {
        super(description, prixInitial, nbExemplaires, isbn, nbPages);
        this.matiere = matiere;
        this.niveauScolaire = niveauScolaire;
    }

    @Override
    public String versFichier() {
        return isbn + System.lineSeparator() + 
               description + ":" + prixInitial + ":" + nbExemplaires + ":" + 
                nbPages + ":" + matiere + ":" + niveauScolaire;
    }

    @Override
    public String toString() {
        return String.format(
                "Manuel [%s] %s | Prix courant: %.2f€ | "
                        + "Stock: %d | Pages: %d | Matière: %s | Niveau: %s", 
                isbn, description, calculerPrix(), nbExemplaires, nbPages, 
                matiere, niveauScolaire);
    }
}
