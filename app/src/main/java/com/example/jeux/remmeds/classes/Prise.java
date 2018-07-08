package com.example.jeux.remmeds.classes;

public class Prise {
    private String nomMedicament;
    private int compartiment;
    private String heurePrise;
    private String dayperso;
    private String listpref;
    private String durationtext;
    private String durationnumber;
    private String drugname;
    private String heureperso;

    public Prise(String nomMedicament, int compartiment, String heurePrise) {
        this.nomMedicament = nomMedicament;
        this.compartiment = compartiment;
        this.heurePrise = heurePrise;
    }

    public Prise() {

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

    public void setDrugname(String drugname) {
        this.drugname = drugname;
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

    public String getDrugname() {
        return drugname;
    }

    public String getHeureperso() {
        return heureperso;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public int getCompartiment() {
        return compartiment;
    }

    public String getHeurePrise() {
        return heurePrise;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public void setCompartiment(int compartiment) {
        this.compartiment = compartiment;
    }

    public void setHeurePrise(String heurePrise) {
        this.heurePrise = heurePrise;
    }
}
