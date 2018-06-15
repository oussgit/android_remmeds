package com.example.jeux.remmeds.classes;

public class Contact {
    private String nom;
    private String prenom;
    private String adresse;
    private String numero;

    public Contact(String nom, String prenom, String adresse, String numero){
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.numero=numero;
        }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumero() {
        return numero;
    }
}
