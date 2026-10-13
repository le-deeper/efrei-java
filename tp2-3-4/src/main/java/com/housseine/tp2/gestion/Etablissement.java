/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.gestion;

import com.housseine.tp2.article.Article;
import com.housseine.tp2.article.Livre;
import com.housseine.tp2.article.Magazine;
import com.housseine.tp2.article.Manuel;
import com.housseine.tp2.depot.BonDepot;
import com.housseine.tp2.depot.LigneDepot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author houss
 */
class Etablissement {
    private String nom;
    private Article[] articles;
    private int nbArticles;
    private BonDepot[] bonsDepot;
    private int nbBons;
    
    private static final int MAX_ARTICLES = 1000;
    private static final int MAX_BONS = 1000;

    public Etablissement(String nom) {
        this.nom = nom;
        this.articles = new Article[MAX_ARTICLES];
        this.nbArticles = 0;
        this.bonsDepot = new BonDepot[MAX_BONS];
        this.nbBons = 0;
    }

    // Gestion interne pour l'insertion ordonnée (Ordre lexicographique par getNumero)
    private void insererArticleTrie(Article nouvelArticle) {
        if (nbArticles >= MAX_ARTICLES) {
            System.out.println("Capacité maximale d'articles atteinte.");
            return;
        }
        
        int i = nbArticles - 1;
        // Déplace les éléments vers la droite pour trouver la bonne place
        while (i >= 0 && articles[i].placerApres(nouvelArticle)) {
            articles[i + 1] = articles[i];
            i--;
        }
        articles[i + 1] = nouvelArticle;
        nbArticles++;
    }

    public Article rechercher(String numero) {
        for (int i = 0; i < nbArticles; i++) {
            if (articles[i].getNumero().equals(numero)) {
                return articles[i];
            }
        }
        return null;
    }

    public void ajouter(String description, double prixInitial, 
            int nbExemplaires, String isbn, int nbPages) {
        Article existant = rechercher(isbn);
        if (existant == null) {
            insererArticleTrie(new Livre(description, prixInitial, 
                    nbExemplaires, isbn, nbPages));
        } else {
            existant.ajouter(nbExemplaires);
        }
    }

    public void ajouter(String description, double prixInitial, 
            int nbExemplaires, String isbn, int nbPages, 
            String matiere, String niveau) {
        Article existant = rechercher(isbn);
        if (existant == null) {
            insererArticleTrie(new Manuel(description, prixInitial, 
                    nbExemplaires, isbn, nbPages, matiere, niveau));
        } else {
            existant.ajouter(nbExemplaires);
        }
    }

    public void ajouter(String description, double prixInitial, 
            int nbExemplaires, String issn, String periodicite, 
            LocalDate datePublication) {
        Article existant = rechercher(issn);
        if (existant == null) {
            insererArticleTrie(new Magazine(description, prixInitial, 
                    nbExemplaires, issn, periodicite, datePublication));
        } else {
            existant.ajouter(nbExemplaires);
        }
    }

    // ici on gère le stock
    public void ajouter(String numero, int qte) {
        Article a = rechercher(numero);
        if (a != null) a.ajouter(qte);
        else System.out.println("Article introuvable.");
    }

    public void retirer(String numero, int qte) {
        Article a = rechercher(numero);
        if (a != null) a.retirer(qte);
        else System.out.println("Article introuvable.");
    }

    // enregistrement de bon de dépôt
    public void ajouter(BonDepot bd) {
        if (nbBons < MAX_BONS) {
            // Insertion triée par date d'émission (le plus récent en dernier -> ordre croissant)
            int i = nbBons - 1;
            while (i >= 0 && bonsDepot[i]
                    .getDateEmission().isAfter(bd.getDateEmission())) {
                bonsDepot[i + 1] = bonsDepot[i];
                i--;
            }
            bonsDepot[i + 1] = bd;
            nbBons++;
            
            // Mise à jour automatique des stocks
            for (int j = 0; j < bd.getNbLignes(); j++) {
                LigneDepot ligne = bd.getLignes()[j];
                ajouter(ligne.getNumero(), ligne.getNbExemplaires());
            }
        }
    }

    // affichages
    public void listerArticlesParQuantite() {
        System.out.println("\n--- Liste des Articles (triés par nombre d'exemplaires croissants) ---");
        Article[] temp = Arrays.copyOf(articles, nbArticles);
        Arrays.sort(temp, new Comparator<Article>() {
            @Override
            public int compare(Article a1, Article a2) {
                return Integer.compare(a1.getNbExemplaires(), 
                        a2.getNbExemplaires());
            }
        });
        
        for (Article a : temp) {
            System.out.println(a.toString());
        }
    }

    public void listerBonsParClient(String telephone) {
        System.out.printf("%n--- Bons de dépôt pour le client %s ---%n", 
                telephone);
        // Déjà trié par date, il suffit de filtrer
        boolean trouve = false;
        for (int i = 0; i < nbBons; i++) {
            if (bonsDepot[i].getNumTelephone().equals(telephone)) {
                System.out.println(bonsDepot[i].toString());
                trouve = true;
            }
        }
        if (!trouve) System.out.println("Aucun dépôt trouvé pour ce numéro.");
    }

    public void listerBonsParArticleEtPeriode(String numero, LocalDate debut, 
            LocalDate fin) {
        System.out.printf(
                "%n--- Bons de dépôt contenant l'article %s entre %s et %s ---%n", 
                numero, debut, fin);
        boolean trouve = false;
        for (int i = 0; i < nbBons; i++) {
            LocalDate d = bonsDepot[i].getDateEmission();
            if (!d.isBefore(debut) && !d.isAfter(fin)) {
                for (int j = 0; j < bonsDepot[i].getNbLignes(); j++) {
                    if (bonsDepot[i].getLignes()[j].getNumero().equals(numero)) {
                        System.out.println(bonsDepot[i].toString());
                        trouve = true;
                        break;
                    }
                }
            }
        }
        if (!trouve) System.out.println("Aucun dépôt correspondant.");
    }

    // fichiers des articles
    public void versFichierArticles(String nomFichier) {
        try (FileWriter fw = new FileWriter(nomFichier, false)) {
            for (int i = 0; i < nbArticles; i++) {
                fw.write(articles[i].versFichier() + System.lineSeparator());
            }
            System.out.println("Articles sauvegardés avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des articles: " + 
                    e.getMessage());
        }
    }

    public void depuisFichierArticles(String nomFichier) {
        File file = new File(nomFichier);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligneRef;
            while ((ligneRef = br.readLine()) != null) {
                if (ligneRef.trim().isEmpty()) continue;
                String numero = ligneRef.trim();
                String ligneDetails = br.readLine();
                
                if (ligneDetails != null) {
                    String[] parts = ligneDetails.split(":");
                    for (int i = 0; i < parts.length; i++) 
                        parts[i] = parts[i].trim();
                    
                    String description = parts[0];
                    double prix = Double.parseDouble(parts[1]);
                    int qte = Integer.parseInt(parts[2]);

                    if (parts.length == 4) {
                        // Livre: Description : prix : nombre d'exemplaires : nombre de pages
                        ajouter(description, prix, qte, numero, 
                                Integer.parseInt(parts[3]));
                    } else if (parts.length == 5) {
                        // Magazine: Description : prix : nombre d'exemplaires : périodicité : date de publication
                        LocalDate datePub = LocalDate.parse(parts[4]);
                        ajouter(description, prix, qte, numero, parts[3], 
                                datePub);
                    } else if (parts.length == 6) {
                        // Manuel: Description : prix : nombre d'exemplaires : nombre de pages : matière : niveau
                        ajouter(description, prix, qte, numero, 
                                Integer.parseInt(parts[3]), parts[4], parts[5]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur de chargement des articles: " + 
                    e.getMessage());
        }
    }

    // Et fichiers des bons de dépôts
    public void versFichierDepots(String nomFichier) {
        try (FileWriter fw = new FileWriter(nomFichier, false)) {
            for (int i = 0; i < nbBons; i++) {
                fw.write(bonsDepot[i].versFichier() + System.lineSeparator());
            }
            System.out.println("Bons de dépôt sauvegardés avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des dépôts: " + 
                    e.getMessage());
        }
    }

    public void depuisFichierDepots(String nomFichier) {
        File file = new File(nomFichier);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String ligneNumBon;
            while ((ligneNumBon = br.readLine()) != null) {
                if (ligneNumBon.trim().isEmpty()) continue;
                int numeroBon = Integer.parseInt(ligneNumBon.trim());
                
                String ligneEntete = br.readLine();
                if (ligneEntete != null) {
                    String[] enteteParts = ligneEntete.split(":");
                    String tel = enteteParts[0].trim();
                    LocalDate dateEm = LocalDate.parse(enteteParts[1].trim());
                    int nLignes = Integer.parseInt(enteteParts[2].trim());
                    
                    BonDepot bd = new BonDepot(numeroBon, tel, dateEm);
                    
                    for (int j = 0; j < nLignes; j++) {
                        String ligneArticle = br.readLine();
                        if (ligneArticle != null) {
                            String[] artParts = ligneArticle.split(":");
                            int qte = Integer.parseInt(artParts[0].trim());
                            String numArt = artParts[1].trim();
                            bd.ajouterLigne(numArt, qte);
                        }
                    }
                    
                    // Ajout manuel au tableau sans déclencher l'ajout d'exemplaires des articles 
                    // (car le stock a déjà été entregistré dans le fichier articles)
                    if (nbBons < MAX_BONS) {
                        bonsDepot[nbBons++] = bd;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur de chargement des dépôts: " + 
                    e.getMessage());
        }
    }
}

