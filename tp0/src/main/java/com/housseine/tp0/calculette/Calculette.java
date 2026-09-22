/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp0.calculette;

/*
 * Nom: El Housseine ABDALLAHI
 * Groupe: BDML 1 App
 * Rôle: Calculette avec gestion d'erreurs
 * Date: 22 Septembre
 */
public class Calculette {

    public float calculer_resultat(int choix_op, float op1, float op2) {
        float res = 0;
        
        switch (choix_op) {
            case 1:
                res = op1 + op2;
                break;
            case 2:
                res = op1 - op2;
                break;
            case 3:
                res = op1 * op2;
                break;
            case 4:
                // on anticipe la division par zéro pour empêcher l'arrêt brutal du programme à l'exécution
                if (op2 != 0) {
                    res = op1 / op2;
                } else {
                    System.out.println("La division par zéro n'est pas autorisé");
                    System.exit(0);
                }
                break;
            case 5:
                if (op2 != 0) {
                    res = op1 % op2;
                } else {
                    System.out.println("Le modulo par zéro n'est pas autorisé");
                    System.exit(0);
                }
                break;
            default:
                break;
        }
        
        return res;
    }
}
