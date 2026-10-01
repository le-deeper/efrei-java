/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp1.ex4;

import java.util.Scanner;

/**
 *
 * @author abdel
 */
public class Ex4Main {
    
    public static void main(String[] args) {
        Magasin magasin = new Magasin();
        magasin.ajouterProduit(new Produit(1, "PC Asus", 1, 1200.50));
        magasin.ajouterProduit(new Produit(2, "Souris Logitech", 2, 49.99));
        magasin.ajouterProduit(new Produit(3, "Clavier Predator", 1, 25));
        magasin.ajouterProduit(new Produit(3, "PS4", 6, 500));

        Client client = new Client(1, "Huu Loc", "huu-loc@email.com");
        Panier panier = new Panier();
        Scanner scanner = new Scanner(System.in);
        int choix;
        int compteurCommandes = 1;

        do {
            System.out.println("\nMenu Magasin");
            System.out.println("1. Afficher les produits disponibles");
            System.out.println("2. Ajouter un produit au panier");
            System.out.println("3. Afficher le panier");
            System.out.println("4. Passer la commande");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    magasin.afficherProduitsDisponibles();
                    break;
                case 2:
                    System.out.print("Entrez le nom du produit a ajouter : ");
                    String nom = scanner.nextLine();
                    Produit p = magasin.trouverProduitParNom(nom);
                    if (p != null) {
                        panier.ajouterProduit(p);
                        System.out.println("Produit ajoute au panier avec succes.");
                    } else {
                        System.out.println("Erreur: Produit introuvable.");
                    }
                    break;
                case 3:
                    panier.afficherPanier();
                    System.out.printf("Total actuel: %f%n", panier.calculerTotal());
                    break;
                case 4:
                    if (panier.getProduits().isEmpty()) {
                        System.out.println("Votre panier est vide. Impossible de passer commande.");
                    } else {
                        Commande commande = new Commande(compteurCommandes++, client, panier);
                        commande.afficherDetailsCommande();
                        panier.getProduits().clear();
                        System.out.println("Commande validee et panier vide.");
                    }
                    break;
                case 5:
                    System.out.println("Fermeture du programme.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        } while (choix != 5);

        scanner.close();
    }
}
