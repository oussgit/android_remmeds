package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.ToggleButton;

import com.example.jeux.remmeds.R;

public class Compartiment extends AppCompatActivity implements View.OnClickListener {


    private Switch swiheureperso;
    private Switch swifrequenceperso;
    private ToggleButton toglundi;
    private ToggleButton togmardi;
    private ToggleButton togmercredi;
    private ToggleButton togjeudi;
    private ToggleButton togvendredi;
    private ToggleButton togsamedi;
    private ToggleButton togdimanche;
    private TextView textoutesles;
    private TextView texapartirde;
    private EditText edinbrheure;
    private EditText ediapartirde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartiment);
        Switch swibreakfast = findViewById(R.id.ptitdej_switch_layout_compartiment);
        swibreakfast.setOnClickListener(this);
        Switch swidejeuner = findViewById(R.id.dejeuner_switch_layout_compartiment);
        swidejeuner.setOnClickListener(this);
        Switch swidiner = findViewById(R.id.diner_switch_layout_compartiment);
        swidiner.setOnClickListener(this);
        Switch swicoucher = findViewById(R.id.coucher_switch_layout_compartiment);
        swicoucher.setOnClickListener(this);
        swiheureperso = findViewById(R.id.heureperso_switch_layout_compartiment);
        swiheureperso.setOnClickListener(this);
        swifrequenceperso = findViewById(R.id.frequenceperso_switch_layout_compartiment);
        swifrequenceperso.setOnClickListener(this);
        texapartirde = findViewById(R.id.fintexte_TextView_layout_compartiment);
        textoutesles = findViewById(R.id.debuttexte_textview_layout_commpartiment);
        edinbrheure = findViewById(R.id.nombreheure_editText_layout_compartiment);
        ediapartirde = findViewById(R.id.heuredepart_editText_layout_compartiment);
        toglundi = findViewById(R.id.lundi_toggleButton_layout_compartiment);
        togmardi = findViewById(R.id.mardi_toggleButton_layout_compartiment);
        togmercredi = findViewById(R.id.mercredi_toggleButton_layout_compartiment);
        togjeudi = findViewById(R.id.jeudi_toggleButton_layout_compartiment);
        togvendredi = findViewById(R.id.vendredi_toggleButton_layout_compartiment);
        togsamedi = findViewById(R.id.samedi_toggleButton_layout_compartiment);
        togdimanche = findViewById(R.id.dimanche_toggleButton_layout_compartiment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ptitdej_switch_layout_compartiment:

                break;
            case R.id.dejeuner_switch_layout_compartiment:

                break;
            case R.id.diner_switch_layout_compartiment:

                break;
            case R.id.coucher_switch_layout_compartiment:

                break;
            case R.id.heureperso_switch_layout_compartiment:
                optionsheureperso();
                break;
            case R.id.frequenceperso_switch_layout_compartiment:
                optionsfrequenceperso();
                break;
            default:
                break;
        }
    }

    private void optionsfrequenceperso() {
        if (swifrequenceperso.isChecked()) {
            toglundi.setVisibility(View.VISIBLE);
            togmardi.setVisibility(View.VISIBLE);
            togmercredi.setVisibility(View.VISIBLE);
            togjeudi.setVisibility(View.VISIBLE);
            togvendredi.setVisibility(View.VISIBLE);
            togsamedi.setVisibility(View.VISIBLE);
            togdimanche.setVisibility(View.VISIBLE);
        } else {
            toglundi.setVisibility(View.GONE);
            togmardi.setVisibility(View.GONE);
            togmercredi.setVisibility(View.GONE);
            togjeudi.setVisibility(View.GONE);
            togvendredi.setVisibility(View.GONE);
            togsamedi.setVisibility(View.GONE);
            togdimanche.setVisibility(View.GONE);
        }
    }

    private void optionsheureperso() {
        if (swiheureperso.isChecked()) {
            texapartirde.setVisibility(View.VISIBLE);
            textoutesles.setVisibility(View.VISIBLE);
            edinbrheure.setVisibility(View.VISIBLE);
            ediapartirde.setVisibility(View.VISIBLE);
        } else {
            texapartirde.setVisibility(View.GONE);
            textoutesles.setVisibility(View.GONE);
            edinbrheure.setVisibility(View.GONE);
            ediapartirde.setVisibility(View.GONE);
        }
    }
}