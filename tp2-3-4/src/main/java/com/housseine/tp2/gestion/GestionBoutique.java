/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.gestion;

import com.housseine.tp2.depot.BonDepot;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author houss
 */
public class GestionBoutique {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Etablissement boutique = new Etablissement("Culture Occasion");
        
        final String FICHIER_ARTICLES = "articles.txt";
        final String FICHIER_DEPOTS = "depots.txt";

        System.out.println("Chargement des données...");
        boutique.depuisFichierArticles(FICHIER_ARTICLES);
        boutique.depuisFichierDepots(FICHIER_DEPOTS);

        boolean continuer = true;

        while (continuer) {
            System.out.println("\n===== BOUTIQUE CULTURE OCCASION =====");
            System.out.println("1. Ajouter un nouveau livre");
            System.out.println("2. Ajouter un nouveau manuel scolaire");
            System.out.println("3. Ajouter un nouveau magazine");
            System.out.println("4. Saisir un bon de dépôt (ajout de stock)");
            System.out.println("5. Lister les articles par stock croissant");
            System.out.println("6. Lister les bons de dépôt par client");
            System.out.println("7. Lister les bons de dépôt par article et période");
            System.out.println("8. Quitter et Sauvegarder");
            System.out.print("Votre choix : ");
            
            int choix = 0;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide.");
                continue;
            }

            switch (choix) {
                case 1: // pour ajouter un nouveau livre
                    System.out.print("ISBN: "); String isbn = scanner.nextLine();
                    System.out.print("Description: "); String descL = scanner.nextLine();
                    System.out.print("Prix initial: "); double prixL = Double.parseDouble(scanner.nextLine());
                    System.out.print("Pages: "); int pagesL = Integer.parseInt(scanner.nextLine());
                    boutique.ajouter(descL, prixL, 0, isbn, pagesL);
                    System.out.println("Livre créé ! (Quantité à zéro, utilisez un bon de dépôt pour l'augmenter)");
                    break;
                case 2: // pour ajouter un nouveau manuel scolaire
                    System.out.print("ISBN: "); String isbnM = scanner.nextLine();
                    System.out.print("Description: "); String descM = scanner.nextLine();
                    System.out.print("Prix initial: "); double prixM = Double.parseDouble(scanner.nextLine());
                    System.out.print("Pages: "); int pagesM = Integer.parseInt(scanner.nextLine());
                    System.out.print("Matière: "); String matiere = scanner.nextLine();
                    System.out.print("Niveau: "); String niveau = scanner.nextLine();
                    boutique.ajouter(descM, prixM, 0, isbnM, pagesM, matiere, niveau);
                    System.out.println("Manuel créé !");
                    break;
                case 3: // pour ajouter un nouveau magazine
                    System.out.print("ISSN: "); String issn = scanner.nextLine();
                    System.out.print("Description: "); String descMag = scanner.nextLine();
                    System.out.print("Prix initial: "); double prixMag = Double.parseDouble(scanner.nextLine());
                    System.out.print("Périodicité (hebdomadaire/mensuel/trimestriel): "); String perio = scanner.nextLine();
                    System.out.print("Date publication (YYYY-MM-DD): "); LocalDate dateP = LocalDate.parse(scanner.nextLine());
                    boutique.ajouter(descMag, prixMag, 0, issn, perio, dateP);
                    System.out.println("Magazine créé !");
                    break;
                case 4: // pour saisir un bon de dépôt (ajout de stock)
                    System.out.print("Numéro de téléphone du déposant: "); String tel = scanner.nextLine();
                    BonDepot bon = new BonDepot(tel);
                    
                    boolean ajoutLigne = true;
                    while (ajoutLigne) {
                        System.out.print("Référence Article (ISBN/ISSN): "); String ref = scanner.nextLine();
                        System.out.print("Quantité déposée: "); int qte = Integer.parseInt(scanner.nextLine());
                        bon.ajouterLigne(ref, qte);
                        
                        System.out.print("Ajouter un autre article dans ce bon ? (o/n): ");
                        if (!scanner.nextLine().equalsIgnoreCase("o")) {
                            ajoutLigne = false;
                        }
                    }
                    boutique.ajouter(bon);
                    System.out.println("Bon de dépôt enregistré et stocks mis à jour !");
                    break;
                case 5: // pour lister les articles par stock croissant
                    boutique.listerArticlesParQuantite();
                    break;
                case 6: // pour lister les bons de dépôt par client
                    System.out.print("Numéro de téléphone: ");
                    boutique.listerBonsParClient(scanner.nextLine());
                    break;
                case 7: // pour lister les bons de dépôt par article et période
                    System.out.print("Référence de l'article: "); String refArt = scanner.nextLine();
                    System.out.print("Date de début (YYYY-MM-DD): "); LocalDate dDeb = LocalDate.parse(scanner.nextLine());
                    System.out.print("Date de fin (YYYY-MM-DD): "); LocalDate dFin = LocalDate.parse(scanner.nextLine());
                    boutique.listerBonsParArticleEtPeriode(refArt, dDeb, dFin);
                    break;
                case 8: // on quitte et on sauvegarde
                    System.out.println("Sauvegarde en cours...");
                    boutique.versFichierArticles(FICHIER_ARTICLES);
                    boutique.versFichierDepots(FICHIER_DEPOTS);
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix non reconnu.");
            }
        }
        scanner.close();
    }
}
