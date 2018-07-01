package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
                CheckBox smsCheck = findViewById(R.id.sms_checkbox_layout_ajoutcontact);
                CheckBox mailCheck = findViewById(R.id.mail_checkbox_layout_ajoutcontact);
                EditText note = findViewById(R.id.notes_edittext_layout_ajoutcontact);

                String userId = "18";
                String nomContact = nom.getText().toString();
                String prenomContact = prenom.getText().toString();
                String numContact = num.getText().toString();
                String mailContact = mail.getText().toString();
                String noteContact = note.getText().toString();
                String smsCheckContact;
                String mailCheckContact;
                if (smsCheck.isChecked()) {
                    smsCheckContact = "1";
                } else {
                    smsCheckContact = "0";
                }
                if (mailCheck.isChecked()) {
                    mailCheckContact = "1";
                } else {
                    mailCheckContact = "0";
                }
                MainActivity.postDoInBackground((userId + "&" + nomContact + "&" + prenomContact + "&" + numContact + "&" + mailContact + "&" + smsCheckContact + "&" + mailCheckContact + "&" + noteContact));
                FragmentRepertoire.addItem(nomContact, prenomContact, mailContact, numContact, mailCheckContact, smsCheckContact, noteContact);
                FragmentRepertoire.refreshRecyclerRep();
                onBackPressed();
            }
        });

    }


}
