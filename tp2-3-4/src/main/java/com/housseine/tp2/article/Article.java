/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.article;

/**
 *
 * @author houss
 */
public abstract class Article {
    protected String description;
    protected double prixInitial;
    protected int nbExemplaires;

    public Article(String description, double prixInitial, int nbExemplaires) {
        this.description = description;
        this.prixInitial = prixInitial;
        this.nbExemplaires = nbExemplaires;
    }

    public abstract String getNumero();
    public abstract double calculerPrix();
    public abstract String versFichier();

    // Compare deux articles selon l'ordre lexicographique de leur numéro
    public boolean placerApres(Article a) {
        return this.getNumero().compareTo(a.getNumero()) > 0;
    }
    
    public void ajouter(int qte) {
        this.nbExemplaires += qte;
    }

    public void retirer(int qte) {
        if (this.nbExemplaires >= qte) {
            this.nbExemplaires -= qte;
        } else {
            System.out.println("Stock insuffisant pour retirer " + qte + " exemplaires.");
        }
    }

    public int getNbExemplaires() {
        return nbExemplaires;
    }

    @Override
    public String toString() {
        return String.format("%s | Prix courant: %.2f€ | Stock: %d", 
                description, calculerPrix(), nbExemplaires);
    }
}