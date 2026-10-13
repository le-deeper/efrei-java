/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp2.article;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author houss
 */
public class Magazine extends Article {
    protected String issn;
    protected String periodicite;
    protected LocalDate datePublication;

    public Magazine(String description, double prixInitial, 
            int nbExemplaires, String issn, String periodicite, 
            LocalDate datePublication) {
        super(description, prixInitial, nbExemplaires);
        this.issn = issn;
        this.periodicite = periodicite;
        this.datePublication = datePublication;
    }

    @Override
    public String getNumero() {
        return issn;
    }

    @Override
    public double calculerPrix() {
        long dureeEcoulee = 0;
        
        // on calcule l'ancienneté selon la périodicité
        if (periodicite.equalsIgnoreCase("hebdomadaire")) {
            dureeEcoulee = datePublication.until(LocalDate.now(), 
                    ChronoUnit.WEEKS);
        } else if (periodicite.equalsIgnoreCase("mensuel")) {
            dureeEcoulee = datePublication.until(LocalDate.now(), 
                    ChronoUnit.MONTHS);
        } else if (periodicite.equalsIgnoreCase("trimestriel")) {
            dureeEcoulee = datePublication.until(LocalDate.now(), 
                    ChronoUnit.MONTHS) / 3;
        }

        // Application des réductions
        if (dureeEcoulee > 4) {
            return prixInitial * 0.25;
        } else if (dureeEcoulee > 2) {
            return prixInitial * 0.50;
        }
        
        return prixInitial;
    }

    @Override
    public String versFichier() {
        return issn + System.lineSeparator() + 
               description + ":" + prixInitial + ":" + nbExemplaires + ":" + 
                periodicite + ":" + datePublication.toString();
    }

    @Override
    public String toString() {
        return String.format(
                "Magazine [%s] %s | Périodicité: %s | Publié le: %s", issn, 
                super.toString(), periodicite, datePublication);
    }
}