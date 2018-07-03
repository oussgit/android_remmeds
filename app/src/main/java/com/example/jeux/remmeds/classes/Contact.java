package com.example.jeux.remmeds.classes;


public class Contact {
    private String nomContact;
    private String prenomContact;
    private String mailContact;
    private String numeroContact;
    private String smsCheck;
    private String mailCheck;
    private String noteContact;
    private String idContact;

    public Contact(String nom, String prenom, String adresse, String numero, String smsCheck, String mailCheck, String noteContact, String idContact) {
        this.nomContact = nom;
        this.prenomContact = prenom;
        this.mailContact = adresse;
        this.numeroContact = numero;
        this.smsCheck = smsCheck;
        this.mailCheck = mailCheck;
        this.noteContact = noteContact;
        this.idContact = idContact;
    }

    public Contact() {

    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public void setNom(String nom) {
        this.nomContact = nom;
    }

    public void setPrenom(String prenom) {
        this.prenomContact = prenom;
    }

    public void setMailContact(String mailContact) {
        this.mailContact = mailContact;
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

    public String getMailContact() {
        return mailContact;
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
