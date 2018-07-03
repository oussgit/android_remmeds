package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.classes.Contact;
import com.example.jeux.remmeds.fragments.FragmentRepertoire;

public class Ajoutcontact extends AppCompatActivity {
    String okTest = "0";
    private int contactPos;
    private String contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutcontact);
        final EditText nom = findViewById(R.id.nom_edittext_layout_ajoutcontact);
        final EditText prenom = findViewById(R.id.prenom_edittext_layout_ajoutcontact);
        final EditText num = findViewById(R.id.numero_edittext_layout_ajoutcontact);
        final EditText mail = findViewById(R.id.adresse_edittext_layout_ajoutcontact);
        final CheckBox smsCheck = findViewById(R.id.sms_checkbox_layout_ajoutcontact);
        final CheckBox mailCheck = findViewById(R.id.mail_checkbox_layout_ajoutcontact);
        final EditText note = findViewById(R.id.notes_edittext_layout_ajoutcontact);
        final Button ajout = findViewById(R.id.ajouter_button_layout_ajoutcontact);
        final Button supprimer = findViewById(R.id.supprimer_button_layout_ajoutcontact);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            okTest = "1";
            nom.setText(getIntent().getExtras().getString("ContactNom"));
            prenom.setText(getIntent().getExtras().getString("ContactPrenom"));
            num.setText(getIntent().getExtras().getString("ContactNum"));
            mail.setText(getIntent().getExtras().getString("ContactMail"));
            note.setText(getIntent().getExtras().getString("ContactNote"));
            contactPos = getIntent().getIntExtra("ContactPos", 0);
            contactId = getIntent().getExtras().getString("ContactId");


            if ((getIntent().getExtras().getString("ContactSmsCheck")).equals("1")) {
                smsCheck.setChecked(true);
            }
            if ((getIntent().getExtras().getString("ContactMailCheck")).equals("1")) {
                mailCheck.setChecked(true);
            }
        }
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentRepertoire.removeItem(contactPos);
                MainActivity.postDoInBackground(("http://212.73.217.202:15020/contact/delete_contact/" + contactId));
                onBackPressed();
            }
        });

        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                if (okTest == "0") {

                    FragmentRepertoire.emptyContact();
                    MainActivity.postDoInBackground(("http://212.73.217.202:15020/contact/add_contact/" + MainActivity.getUserID() + "&" + nomContact + "&" + prenomContact + "&" + numContact + "&" + mailContact + "&" + smsCheckContact + "&" + mailCheckContact + "&" + noteContact));
                    FragmentRepertoire.fillRecyclerRep(MainActivity.getUserID());
                    FragmentRepertoire.refreshRecyclerRep();
                    onBackPressed();

                } else {
                    Contact contact1 = new Contact();
                    contact1 = FragmentRepertoire.contactList.get(contactPos);
                    contact1.setNom(nomContact);
                    contact1.setPrenom(prenomContact);
                    contact1.setMailContact(mailContact);
                    contact1.setNoteContact(noteContact);
                    if (smsCheck.isChecked()) {
                        contact1.setSmsCheck("1");
                    } else {
                        contact1.setSmsCheck("0");
                    }
                    if (mailCheck.isChecked()) {
                        contact1.setMailCheck("1");
                    } else {
                        contact1.setMailCheck("0");
                    }
                    FragmentRepertoire.changeItemRecyclerRep(contactPos);
                    MainActivity.postDoInBackground(("http://212.73.217.202:15020/contact/update_contact/" + contactId + "&" + nomContact + "&" + prenomContact + "&" + numContact + "&" + mailContact + "&" + smsCheckContact + "&" + mailCheckContact + "&" + noteContact));
                    onBackPressed();
                }
            }
        });
    }
}

