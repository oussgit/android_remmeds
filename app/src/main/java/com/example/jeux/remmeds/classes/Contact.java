package com.example.jeux.remmeds.classes;


import android.util.Log;

import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.fragments.FragmentRepertoire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public Contact(JSONArray array, int jsonIndex) {
        try {
            this.setNom(array.getJSONObject(jsonIndex).getString("lastname"));
            this.setPrenom(array.getJSONObject(jsonIndex).getString("firstname"));
            this.setMailContact(array.getJSONObject(jsonIndex).getString("mail"));
            this.setNumero(array.getJSONObject(jsonIndex).getString("phonenumber"));
            this.setMailCheck(array.getJSONObject(jsonIndex).getString("chx_mail"));
            this.setSmsCheck(array.getJSONObject(jsonIndex).getString("chx_sms"));
            this.setNoteContact(array.getJSONObject(jsonIndex).getString("note"));
            this.setIdContact(array.getJSONObject(jsonIndex).getString("contact_id"));
            FragmentRepertoire.addContact(this);
        } catch (JSONException e) {
            Log.e("Contact", "Erreur" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("Contact", "NULL JSON OBject" + e);
        }
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
