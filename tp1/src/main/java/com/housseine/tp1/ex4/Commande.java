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
public class Commande {
    private int idCommande;
    private Client client;
    private List<Produit> produitsCommandes;
    private double total;

    public Commande(int idCommande, Client client, Panier panier) {
        this.idCommande = idCommande;
        this.client = client;
        this.produitsCommandes = new ArrayList<>(panier.getProduits());
        this.total = panier.calculerTotal();
    }

    public void afficherDetailsCommande() {
        System.out.printf("--------- Commande %d ---------%n", idCommande);
        client.afficherDetails();
        System.out.println("Produits commandes :");
        for (Produit p : produitsCommandes) {
            System.out.printf("\t- %s | Quantite: %d | Prix: %f%n", p.getNom(), p.getQt(), p.getPrix());
        }
        System.out.printf("Total de la commande : %f%n", total);
        System.out.println("---------------------------\n");
    }

    public int getIdCommande() { return idCommande; }
    public Client getClient() { return client; }
    public List<Produit> getProduitsCommandes() { return produitsCommandes; }
    public double getTotal() { return total; }

    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }
    public void setClient(Client client) { this.client = client; }
    public void setProduitsCommandes(List<Produit> produitsCommandes) { this.produitsCommandes = produitsCommandes; }
    public void setTotal(double total) { this.total = total; }
}
