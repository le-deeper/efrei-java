/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp1.ex4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdel
 */
public class Panier {
    private List<Produit> produits = new ArrayList<>();
    
    public void ajouterProduit(Produit p) {
        produits.add(p);
    }
    
    public void supprimerProduit(Produit p) {
        produits.remove(p);
    }
    
    public void afficherPanier() {
        System.out.println("\nPanier:");
        for (Produit p: produits) 
            System.out.printf(
                    "\tProduit %d: Nom -> %s | Quantite -> %d | Prix: %f%n", 
                    p.getId(), p.getNom(), p.getQt(), p.getPrix());
    }
    
    public double calculerTotal() {
        double total = 0;
        for (Produit p: produits)
            total += p.getQt() * p.getPrix();
        return total;
    }

    public List<Produit> getProduits() { 
        return produits;}
}