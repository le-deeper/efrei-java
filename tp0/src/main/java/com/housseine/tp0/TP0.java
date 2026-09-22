/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.housseine.tp0;

import com.housseine.tp0.calculette.Calculette;
import java.util.Scanner;

/*
    * Nom: El Housseine ABDALLAHI
    * Groupe: BDML 1 App
    * Rôle: Découverte de la saisie utilisateur et débogage
    * Date: 22 Septembre
*/
public class TP0 {

    public static void main(String[] args) {
        // TP0.exo1();
        // TP0.exo2();
         TP0.calculer();
    }
    
    
    public static void exo1() {
        Scanner sc = new Scanner(System.in);
        String prenom;

        System.out.println("Bonjour, quel est votre prenom?");
        prenom = sc.nextLine();
        System.out.println("Bonjour " + prenom + ", bienvenue sur NetBeans !");
        sc.close();
    }
    
    
    public static void exo2() {
        int nb = 5;
        int result = 0;
        int ind = 1;

        while (ind <= nb) {
            result = result + ind;
            ind++; 
        }

        System.out.println();
        System.out.printf("La somme des %d entiers est: %d%n", nb,result);
    }
    
    
    public static void calculer() {
        Scanner sc = new Scanner(System.in);
        Calculette calculette = new Calculette();

        System.out.println("Please enter the operator:");
        System.out.println("1) add");
        System.out.println("2) substract");
        System.out.println("3) multiply");
        System.out.println("4) divide");
        System.out.println("5) modulo");
        
        int choix_op = sc.nextInt();

        // petit vérif
        if (choix_op < 1 || choix_op > 5) {
            System.out.println("Opérateur invalide, il faut choisir choisir entre 1 et 5.");
            System.exit(0);
        }

        System.out.println("Please enter the first number:");
        float op1 = sc.nextFloat();

        System.out.println("Please enter the second number:");
        float op2 = sc.nextFloat();

        float res_calc = calculette.calculer_resultat(choix_op, op1, op2);
        System.out.println("The result is: " + res_calc);

        sc.close();
    }
}
