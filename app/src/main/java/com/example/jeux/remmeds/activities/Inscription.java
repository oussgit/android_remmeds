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
        Button valider_button = findViewById(R.id.valider_button_layout_inscription);
        valider_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText field_adresse_mail = findViewById(R.id.mail_edittext_layout_inscription);
                EditText field_password = findViewById(R.id.password_edittext_layout_inscription);
                EditText field_password_conf = findViewById(R.id.passwordconf_edittext_layout_inscription);
                CheckBox accepte_CU = findViewById(R.id.conditions_checkbox_layout_inscription);
                String adresse_mail = field_adresse_mail.getText().toString();
                String password = field_password.getText().toString();
                String password_conf = field_password_conf.getText().toString();
                //we will have to add a check on the mail address in the future: if exit in DB then raise error
                if (conditions_are_ok(accepte_CU, adresse_mail, password, password_conf)) {
                    Intent set_profil = new Intent(Inscription.this, Setup.class);
                    startActivity(set_profil);
                    finish();
                } else {
                    alert_dialog_password_missmatch_error();
                }
            }

            private boolean conditions_are_ok(CheckBox accepte_CU, String adresse_mail, String password, String password_conf) {
                return password.equals(password_conf) &&
                        check_valid_mail_address(adresse_mail) &&
                        accepte_CU.isChecked() && !password.equals("");
            }
        });
    }


    private Boolean check_valid_mail_address(String my_email) {
        Pattern emailPattern = Pattern.compile(
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher check = emailPattern.matcher(my_email);
        if (check.matches()) {
            return true;
        }
        return false;
    }


    private void alert_dialog_password_missmatch_error() {
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
