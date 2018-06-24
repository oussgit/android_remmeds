package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.jeux.remmeds.R;

public class Debug extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }

    public void goajoutcontact (View view){
        startActivity(new Intent(Debug.this, Ajoutcontact.class));
    }

    public void goauthentification (View view){
        startActivity(new Intent(Debug.this, Authentification.class));
    }

    public void goinscription (View view){
        startActivity(new Intent(Debug.this, Inscription.class));
    }

    public void gocompartiment (View view){
        startActivity(new Intent(Debug.this, Compartiment.class));
    }

    public void gosetup (View view){
        startActivity(new Intent(Debug.this, Setup.class));
    }
}
