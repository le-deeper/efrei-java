/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.depot;

import java.time.LocalDate;

/**
 *
 * @author houss
 */
public class BonDepot {
    private static int compteurUnique = 1;
    
    protected int numeroBon;
    protected String numTelephone;
    protected LocalDate dateEmission;
    protected LigneDepot[] lignes;
    protected int nbLignes;
    private static final int MAX_LIGNES = 50;
    
    public BonDepot(String numTelephone) {
        this.numeroBon = compteurUnique++;
        this.numTelephone = numTelephone;
        this.dateEmission = LocalDate.now(); // auto
        this.lignes = new LigneDepot[MAX_LIGNES];
        this.nbLignes = 0;
    }

    // Constructeur utilisé lors du chargement depuis un fichier
    public BonDepot(int numeroBon, String numTelephone, LocalDate dateEmission) {
        this.numeroBon = numeroBon;
        this.numTelephone = numTelephone;
        this.dateEmission = dateEmission;
        this.lignes = new LigneDepot[MAX_LIGNES];
        this.nbLignes = 0;
        
        // Met à jour le compteur global si nécessaire pour éviter les doublons futurs
        if (numeroBon >= compteurUnique) {
            compteurUnique = numeroBon + 1;
        }
    }

    public void ajouterLigne(String numero, int qte) {
        if (nbLignes < MAX_LIGNES) {
            lignes[nbLignes++] = new LigneDepot(numero, qte);
        } else {
            System.out.println("Erreur: Ce bon de dépôt a atteint sa limite de lignes.");
        }
    }

    public LocalDate getDateEmission() { return dateEmission; }
    public String getNumTelephone() { return numTelephone; }
    public LigneDepot[] getLignes() { return lignes; }
    public int getNbLignes() { return nbLignes; }

    public String versFichier() {
        StringBuilder sb = new StringBuilder();
        sb.append(numeroBon).append(System.lineSeparator());
        sb
            .append(numTelephone).append(":")
            .append(dateEmission.toString())
            .append(":")
            .append(nbLignes)
            .append(System.lineSeparator());
        for (int i = 0; i < nbLignes; i++) {
            sb.append(lignes[i].versFichier());
            if (i < nbLignes - 1) sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bon de dépôt N°").append(numeroBon)
          .append(" | Tél: ").append(numTelephone)
          .append(" | Date: ").append(dateEmission).append("\n");
        for (int i = 0; i < nbLignes; i++) {
            sb.append("  ").append(lignes[i].toString()).append("\n");
        }
        return sb.toString();
    }
}