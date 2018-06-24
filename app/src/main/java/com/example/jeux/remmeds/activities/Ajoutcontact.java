package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.fragments.FragmentRepertoire;

public class Ajoutcontact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutcontact);

        Button ajout = findViewById(R.id.ajouter_button_layout_ajoutcontact);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nom = findViewById(R.id.nom_edittext_layout_ajoutcontact);
                EditText prenom = findViewById(R.id.prenom_edittext_layout_ajoutcontact);
                EditText num = findViewById(R.id.numero_edittext_layout_ajoutcontact);
                EditText mail = findViewById(R.id.adresse_edittext_layout_ajoutcontact);

                String contactNom = nom.getText().toString();
                String contactPrenom = prenom.getText().toString();
                String contactNum = num.getText().toString();
                String contactMail = mail.getText().toString();
                FragmentRepertoire.addItem(contactNom, contactPrenom, contactNum, contactMail);
            }
        });

    }




}
