/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp1.ex1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdel
 */
public class Personne {
    private String nom;
    private String prenom;
    private int anNaissance;
    private static int NB_PERSONNES_CREES = 0;
    
    public Personne (String nom, String prenom, int annee) {
        this.nom = nom;
        this.prenom = prenom;
        this.anNaissance = annee;
        NB_PERSONNES_CREES += 1;
        this.afficherNbPers();
    }
    
    public Personne (String prenom, int annee) {
        this("Inconnu", prenom, annee);
    }
    
    public Personne () {
        this("Potter", "Harry", 1980);
    }
    
    public String getNom() {
        return nom;   
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public int getAnNaissance() {
        return anNaissance;
    }
    
    public void setNom(String nom) {
        this.nom = nom;   
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }
    
    public int calculerAge() { return 2026 - this.anNaissance; }
    
    public void afficherInfos() {
        System.out.printf("Nom: %s%nPrenom: %s%nAge: %d%n", 
                this.nom, this.prenom, this.calculerAge());
    }
    
    public void mange(String aliment) {
        System.out.printf("%s %s mange une/un %s.%n", this.nom, this.prenom,
                aliment);
    }
    
    public static void main(String[] args) {
        Personne personne1 = new Personne("Dupont", "Jean",2004);
        Personne p2 = new Personne("Motta", 1998);
        Personne p3 = new Personne();
        
        List<Personne> personnes = new ArrayList<>(List.of(personne1, p2, p3));
        for (Personne p: personnes) {
            System.out.println("--------------Personne--------------");
            System.out.printf("Données Récupérées: Nom -> %s | Prenom -> %s | Année de naissance: %d | Age: %d%n",
                    p.getNom(), p.getPrenom(), p.getAnNaissance(), p.calculerAge());
            System.out.println("Changement des valeurs des attributs...");
            p.setNom("Nouveau nom");
            p.setPrenom("Nouveau prénom");
            p.setAnNaissance(1990);
            System.out.println("\nAffichage des nouveaux infos:");
            p.afficherInfos();
            System.out.printf("--------------Fin--------------%n%n");
        }
    }
    
    public void afficherNbPers() {
        System.out.printf("Au total, %d personnes ont été créées.%n", 
                Personne.NB_PERSONNES_CREES);
    }
}
