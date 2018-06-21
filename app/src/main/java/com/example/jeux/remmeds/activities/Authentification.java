package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.jeux.remmeds.R;

public class Authentification extends AppCompatActivity implements View.OnClickListener {

    private TextView alertTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        Button connexion_button = findViewById(R.id.connexion_button_layout_authentification);
        Button sinscrire_button = findViewById(R.id.sinscrire_button_layout_authentification);
        Button problemeconnexion_button = findViewById(R.id.problemeconnexion_button_layout_authentification);
        connexion_button.setOnClickListener(this);
        sinscrire_button.setOnClickListener(this);
        problemeconnexion_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connexion_button_layout_authentification:
                EditText user = findViewById(R.id.mail_edittext_layout_authentification);
                EditText password = findViewById(R.id.password_edittext_layout_authentification);
                if (authentication_check(user, password)) {
                    Intent main_activity = new Intent(Authentification.this, MainActivity.class);
                    startActivity(main_activity);
                } else {
                    alert_dialog_authentication();
                }
                break;
            case R.id.sinscrire_button_layout_authentification:
                Intent signup_activity = new Intent(Authentification.this, Inscription.class);
                startActivity(signup_activity);
                break;
            case R.id.problemeconnexion_button_layout_authentification:
                alert_dialog_connection_error();
                break;
        }
    }

    private void alert_dialog_authentication() {
        //Display an alert dialog for credential error
        AlertDialog.Builder builder = new AlertDialog.Builder(Authentification.this);

        builder.setCancelable(true);
        builder.setTitle("Erreur d'authentification");
        builder.setMessage("\nVotre compte / mot de passe est erroné.\n\nVeuillez cliquer sur Ok et réessayer.");

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private void alert_dialog_connection_error() {
        //Display an alert dialog for credential error
        AlertDialog.Builder builder = new AlertDialog.Builder(Authentification.this);

        builder.setCancelable(true);
        builder.setTitle("Problème de connexion ?");
        builder.setMessage("Cliquez sur le bouton ci-joint pour envoyer un mail au support " +
                "   de notre application ou via notre site web.\n(Rubrique: Nous contacter)");

        builder.setNegativeButton("Envoyer mail", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mailto = "mailto:remmeds@outlook.fr" +
                        "?subject=" + Uri.encode("Problème de connexion Application RemMeds");
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Visiter site web", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String website_address = "http://212.73.217.202:10080/"; //NOSONAR
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(website_address));
                startActivity(viewIntent);
            }
        });

        builder.show();
    }

    private boolean authentication_check(EditText user, EditText password) {
        //ATM hardcoded credentials awaiting database connection
        return user.getText().toString().equals("root") && password.getText().toString().equals("");
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter l'application ?")
                .setMessage("\nÊtes-vous sûr de vouloir quitter l'application ?")
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
