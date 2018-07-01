package com.example.jeux.remmeds.classes;


public class Contact {
    private String nomContact;
    private String prenomContact;
    private String adresseContact;
    private String numeroContact;
    private String smsCheck;
    private String mailCheck;
    private String noteContact;

    public Contact(String nom, String prenom, String adresse, String numero, String smsCheck, String mailCheck, String noteContact) {
        this.nomContact = nom;
        this.prenomContact = prenom;
        this.adresseContact = adresse;
        this.numeroContact = numero;
        this.smsCheck = smsCheck;
        this.mailCheck = mailCheck;
        this.noteContact = noteContact;

    }

    public Contact() {

    }

    public void setNom(String nom) {
        this.nomContact = nom;
    }

    public void setPrenom(String prenom) {
        this.prenomContact = prenom;
    }

    public void setAdresseContact(String adresseContact) {
        this.adresseContact = adresseContact;
    }

    public void setNumero(String numero) {
        this.numeroContact = numero;
    }

    public String getNom() {
        return nomContact;
    }

    public String getPrenom() {
        return prenomContact;
    }

    public String getAdresseContact() {
        return adresseContact;
    }

    public String getNumero() {
        return numeroContact;
    }

    public void setSmsCheck(String smsCheck) {
        this.smsCheck = smsCheck;
    }

    public void setMailCheck(String mailCheck) {
        this.mailCheck = mailCheck;
    }

    public void setNoteContact(String noteContact) {
        this.noteContact = noteContact;
    }

    public String getSmsCheck() {
        return smsCheck;
    }

    public String getMailCheck() {
        return mailCheck;
    }

    public String getNoteContact() {
        return noteContact;
    }
}
