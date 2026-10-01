/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.housseine.tp1.ex4;

/**
 *
 * @author abdel
 */
public class Client {
    private int id;
    private String nom;
    private String email;
    
    public void afficherDetails() {
        System.out.printf("-------Client %d-------%nNom: %s%n " + 
                "email: %s%n-------------------%n%n", id, nom, email);
    }
    
    public Client(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getId() {
        return id;
    }
    
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }
    public void setId(int id) { this.id = id; }
}
