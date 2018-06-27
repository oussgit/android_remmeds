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

    public void displayPage (View view){
        switch(view.getId()){
            default:
                break;
            case R.id.authentification_button_debug:
                startActivity(new Intent(Debug.this, Authentification.class));
                break;
            case R.id.inscription_button_debug:
                startActivity(new Intent(Debug.this, Inscription.class));
                break;
            case R.id.compartiment_button_debug:
                startActivity(new Intent(Debug.this, Compartiment.class));
                break;
            case R.id.ajoutcontact_button_debug:
                startActivity(new Intent(Debug.this, Ajoutcontact.class));
                break;
            case R.id.setup_button_debug:
                startActivity(new Intent(Debug.this, Setup.class));
                break;
        }
    }
}
