/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp1.ex4;

/**
 *
 * @author abdel
 */
public class Produit {
    private int id;
    private String nom;
    private int qt;
    private double prix;
    
    public void afficherDetails() {
        System.out.printf("-------Produit %d-------%nNom: %s%n" + 
                "Quantité: %d%nPrix: %f%n------------------%n%n", id, nom, qt, prix);
    }
    
    public Produit(int id, String nom, int qt, double prix) {
        this.id = id;
        this.nom = nom;
        this.qt = qt;
        this.prix = prix;
    }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getQt() { return qt; }
    public double getPrix() { return prix; }
    
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setQt(int qt) { this.qt = qt; }
    public void setPrix(double prix) { this.prix = prix; }
    
    
}
