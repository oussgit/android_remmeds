package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.jeux.remmeds.R;

public class debug extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }

    public void goajoutcontact (View view){
        startActivity(new Intent(debug.this, Ajoutcontact.class));
    }

    public void goauthentification (View view){
        startActivity(new Intent(debug.this, Authentification.class));
    }

    public void goinscription (View view){
        startActivity(new Intent(debug.this, Inscription.class));
    }

    public void gocompartiment (View view){
        startActivity(new Intent(debug.this, Compartiment.class));
    }
}
