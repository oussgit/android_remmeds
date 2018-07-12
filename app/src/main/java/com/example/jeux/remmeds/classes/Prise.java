package com.example.jeux.remmeds.classes;

import com.example.jeux.remmeds.fragments.FragmentAccueil;

public class Prise {
    private String nommedicament;
    private int compartiment;
    private String heurePrise;
    private String dayperso;
    private String listpref;
    private String durationtext;
    private String durationnumber;
    private String heureperso;
    private String datePrise;
    private String isTaken;

    public Prise(String nomMedicament, int compartiment, String heurePrise){
        this.nommedicament = nomMedicament;
        this.compartiment = compartiment;
        this.heurePrise = heurePrise;
    }

    public Prise(String nomMedicament, int compartiment, String heurePrise, String datePrise, String isTaken){
        this.nommedicament = nomMedicament;
        this.compartiment = compartiment;
        this.heurePrise = heurePrise;
        this.datePrise = datePrise;
        this.isTaken = isTaken;
    }


    public Prise() {

    }

    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }

    public String getIsTaken() {

        return isTaken;
    }

    public void setDatePrise(String datePrise) {
        this.datePrise = datePrise;
    }

    public String getDatePrise() {
        return datePrise;
    }

    public void setDayperso(String dayperso) {
        this.dayperso = dayperso;
    }

    public void setListpref(String listpref) {
        this.listpref = listpref;
    }

    public void setDurationtext(String durationtext) {
        this.durationtext = durationtext;
    }

    public void setDurationnumber(String durationnumber) {
        this.durationnumber = durationnumber;
    }

    public void setHeureperso(String heureperso) {
        this.heureperso = heureperso;
    }

    public String getDayperso() {
        return dayperso;
    }

    public String getListpref() {
        return listpref;
    }

    public String getDurationtext() {
        return durationtext;
    }

    public String getDurationnumber() {
        return durationnumber;
    }

    public String getHeureperso() {
        return heureperso;
    }

    public String getNommedicament() {
        return nommedicament;
    }

    public int getCompartiment() {
        return compartiment;
    }

    public String getHeurePrise() {
        return heurePrise;
    }

    public void setNommedicament(String nommedicament) {
        this.nommedicament = nommedicament;
    }

    public void setCompartiment(int compartiment) {
        this.compartiment = compartiment;
    }

    public void setHeurePrise(String heurePrise) {
        this.heurePrise = heurePrise;
    }
}