package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private TextView texnommedic;
    private EditText edinbrheure;
    private EditText ediapartirde;
    private EditText nbrduree;
    private EditText note;
    private Switch swibreakfast;
    private Switch swidejeuner;
    private Switch swidiner;
    private Switch swicoucher;
    private Spinner typeduree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartiment);
        swibreakfast = findViewById(R.id.ptitdej_switch_layout_compartiment);
        swidejeuner = findViewById(R.id.dejeuner_switch_layout_compartiment);
        swidiner = findViewById(R.id.diner_switch_layout_compartiment);
        swicoucher = findViewById(R.id.coucher_switch_layout_compartiment);
        swiheureperso = findViewById(R.id.heureperso_switch_layout_compartiment);
        swifrequenceperso = findViewById(R.id.frequenceperso_switch_layout_compartiment);

        typeduree = findViewById(R.id.typenombre_spinner_layout_compartiment);

        note = findViewById(R.id.note_editText_layout_compartiment);
        nbrduree = findViewById(R.id.nombre_editText_layout_compartiment);
        texapartirde = findViewById(R.id.fintexte_TextView_layout_compartiment);
        textoutesles = findViewById(R.id.debuttexte_textview_layout_commpartiment);
        texnommedic = findViewById(R.id.nom_editText_layout_compartiment);
        edinbrheure = findViewById(R.id.nombreheure_editText_layout_compartiment);
        ediapartirde = findViewById(R.id.heuredepart_editText_layout_compartiment);

        toglundi = findViewById(R.id.lundi_toggleButton_layout_compartiment);
        togmardi = findViewById(R.id.mardi_toggleButton_layout_compartiment);
        togmercredi = findViewById(R.id.mercredi_toggleButton_layout_compartiment);
        togjeudi = findViewById(R.id.jeudi_toggleButton_layout_compartiment);
        togvendredi = findViewById(R.id.vendredi_toggleButton_layout_compartiment);
        togsamedi = findViewById(R.id.samedi_toggleButton_layout_compartiment);
        togdimanche = findViewById(R.id.dimanche_toggleButton_layout_compartiment);


        swibreakfast.setOnClickListener(this);
        swidejeuner.setOnClickListener(this);
        swidiner.setOnClickListener(this);
        swicoucher.setOnClickListener(this);
        swiheureperso.setOnClickListener(this);
        swifrequenceperso.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (getIntent().getExtras().getString("days") != null) {
                setUpDaysPerso(getIntent().getExtras().getString("days"));
            }
            if (getIntent().getExtras().getString("list_pref") != null) {
                setUpTimePerso(getIntent().getExtras().getString("list_pref"));
            }
            setUpDureePerso(getIntent().getExtras().getString("duration_text","Jours"));
            nbrduree.setText(getIntent().getExtras().getString("duration_number","1"));
            texnommedic.setText(getIntent().getExtras().getString("drug_name", "Nom du medicament"));
            note.setText(getIntent().getExtras().getString("note","Notes"));
        }
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

    private void setUpDureePerso(String dureePref){
        String[] items = dureePref.split(",");
        for (String item : items) {
            switch (item) {
                case "Jours":
                    typeduree.setSelection(1);
                    break;
                case "Semaines":
                    typeduree.setSelection(2);
                    break;
                case "Mois":
                    typeduree.setSelection(3);
                    break;
                case "Années":
                    typeduree.setSelection(4);
                    break;
                default:
                    break;
            }
        }
    }

    private void setUpTimePerso(String timePref){
        String[] items = timePref.split(",");
        for (String item : items) {
            switch (item) {
                case "Breakfast":
                    swibreakfast.setChecked(true);
                    break;
                case "Lunch":
                    swidejeuner.setChecked(true);
                    break;
                case "Dinner":
                    swidiner.setChecked(true);
                    break;
                case "Bedtime":
                    swicoucher.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    private void setUpDaysPerso(String daysPref) {
        String[] items = daysPref.split(",");
        swifrequenceperso.setChecked(true);
        optionsfrequenceperso();
        for (String item : items) {
            switch (item) {
                case "Lundi":
                    toglundi.setChecked(true);
                    break;
                case "Mardi":
                    togmardi.setChecked(true);
                    break;
                case "Merecredi":
                    togmercredi.setChecked(true);
                    break;
                case "Jeudi":
                    togjeudi.setChecked(true);
                    break;
                case "Vendredi":
                    togvendredi.setChecked(true);
                    break;
                case "Samedi":
                    togsamedi.setChecked(true);
                    break;
                case "Dimanche":
                    togdimanche.setChecked(true);
                    break;
            }
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