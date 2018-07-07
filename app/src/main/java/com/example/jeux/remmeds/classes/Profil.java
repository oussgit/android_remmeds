package com.example.jeux.remmeds.classes;

public class Profil {
    private String lastname;
    private String firstname;
    private String mail;
    private String breakfastHour;
    private String lunchHour;
    private String dinnerHour;
    private String bedHour;
    private String password;


    public Profil(String lastname, String firstname, String mail, String password, String breakfastHour, String lunchHour, String dinnerHour, String bedHour) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.mail = mail;
        this.password = password;
        this.breakfastHour = breakfastHour;
        this.lunchHour = lunchHour;
        this.dinnerHour = dinnerHour;
        this.bedHour = bedHour;

    }

    public Profil(){}

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBreakfastHour() {
        return breakfastHour;
    }

    public void setBreakfastHour(String breakfastHour) {
        this.breakfastHour = breakfastHour;
    }

    public String getLunchHour() {
        return lunchHour;
    }

    public void setLunchHour(String lunchHour) {
        this.lunchHour = lunchHour;
    }

    public String getDinnerHour() {
        return dinnerHour;
    }

    public void setDinnerHour(String dinnerHour) {
        this.dinnerHour = dinnerHour;
    }

    public String getBedHour() {
        return bedHour;
    }

    public void setBedHour(String bedHour) {
        this.bedHour = bedHour;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}