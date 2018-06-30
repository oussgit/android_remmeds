package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.jeux.remmeds.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Button validerButton = findViewById(R.id.valider_button_layout_inscription);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fieldAdresseMail = findViewById(R.id.mail_edittext_layout_inscription);
                EditText fieldPassword = findViewById(R.id.password_edittext_layout_inscription);
                EditText fieldPasswordConf = findViewById(R.id.passwordconf_edittext_layout_inscription);
                CheckBox accepteCU = findViewById(R.id.conditions_checkbox_layout_inscription);
                String adresseMail = fieldAdresseMail.getText().toString();
                String password = fieldPassword.getText().toString();
                String passwordConf = fieldPasswordConf.getText().toString();
                //we will have to add a check on the mail address in the future: if exit in DB then raise error
                if (conditionsAreOk(accepteCU, adresseMail, password, passwordConf)) {
                    Intent setProfil = new Intent(Inscription.this, Setup.class);
                    startActivity(setProfil);
                    finish();
                } else {
                    alertDialogPasswordMissmatchError();
                }
            }

            private boolean conditionsAreOk(CheckBox accepteCU, String adresseMail, String password, String passwordConf) {
                return password.equals(passwordConf) &&
                        checkValidMailAddress(adresseMail) &&
                        accepteCU.isChecked() && !password.equals("");
            }
        });
    }


    private Boolean checkValidMailAddress(String myEmail) {
        Pattern emailPattern = Pattern.compile(
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher check = emailPattern.matcher(myEmail);
        return check.matches();
    }


    private void alertDialogPasswordMissmatchError() {
        //Display an alert dialog for missmatch error
        AlertDialog.Builder builder = new AlertDialog.Builder(Inscription.this);

        builder.setCancelable(true);
        builder.setTitle("Problème d'inscription");
        builder.setMessage("L'adresse mail n'est pas valide ou les mots de passe ne sont pas identiques");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Annuler inscription ?")
                .setMessage("\nÊtes-vous sûr de vouloir annuler l'inscription ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Non", null)
                .show();
    }
}
