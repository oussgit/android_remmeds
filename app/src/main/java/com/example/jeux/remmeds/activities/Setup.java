package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.jeux.remmeds.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        final EditText fieldPrenom = findViewById(R.id.prenom_edittext_layout_setup);
        final EditText fieldNom = findViewById(R.id.nom_edittext_layout_setup);
        final EditText fieldBreakfast = findViewById(R.id.breakfast_editText_layout_setup);
        final EditText fieldLunch = findViewById(R.id.lunch_edittext_layout_setup);
        final EditText fieldDinner = findViewById(R.id.dinner_edittext_layout_setup);
        final EditText fieldBedtime = findViewById(R.id.bedtime_edittext_layout_setup);
        getFormattedHour(fieldBreakfast);
        getFormattedHour(fieldLunch);
        getFormattedHour(fieldDinner);
        getFormattedHour(fieldBedtime);
        Button validerButton = findViewById(R.id.enregistrer_button_layout_setup);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(fieldPrenom.getText()) && !TextUtils.isEmpty(fieldNom.getText()) &&
                        !TextUtils.isEmpty(fieldBreakfast.getText()) && !TextUtils.isEmpty(fieldLunch.getText()) &&
                        !TextUtils.isEmpty(fieldDinner.getText()) && !TextUtils.isEmpty(fieldBedtime.getText())) {
                    //ATM  do nothing, Future add values in DB 
                    finish();
                } else {
                    alertDialogMissingFieldError();
                }
            }
        });
    }


    private void getFormattedHour(final EditText whichField) {
        //Launch a time picker instead of keyboard on clock field
        whichField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar myCurrentTime = Calendar.getInstance();
                int hour = myCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = myCurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Setup.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        DecimalFormat addZero = new DecimalFormat("00");
                        whichField.setText(addZero.format(selectedHour) + ":" + addZero.format(selectedMinute)); //NOSONAR
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Sélectionner heure");
                mTimePicker.show();

            }
        });
    }


    private void alertDialogMissingFieldError() {
        //Display an alert dialog for missing field
        AlertDialog.Builder builder = new AlertDialog.Builder(Setup.this);

        builder.setCancelable(true);
        builder.setTitle("Problème");
        builder.setMessage("\nVeuillez renseigner tous les champs avant de valider");
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
                .show();
    }
}
