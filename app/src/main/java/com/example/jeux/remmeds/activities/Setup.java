package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class Setup extends AppCompatActivity {

    String mailAccount;
    String passwordAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Intent inscriptionActivity = getIntent();
        mailAccount = inscriptionActivity.getStringExtra("mailAccount");
        passwordAccount = inscriptionActivity.getStringExtra("passwordAccount");
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
                    String prenom = fieldPrenom.getText().toString();
                    String nom = fieldNom.getText().toString();
                    String breakfast = fieldBreakfast.getText().toString();
                    String lunch = fieldLunch.getText().toString();
                    String dinner = fieldDinner.getText().toString();
                    String bedTime = fieldBedtime.getText().toString();
                    String createAccountURL = "http://212.73.217.202:15020/user/create_account/" + mailAccount + "&" + passwordAccount + "&" + nom + "&" + prenom + "&" + breakfast + "&" + lunch + "&" + dinner + "&" + bedTime;

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.post(createAccountURL, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int arg0,
                                              Header[] arg1, byte[] arg2) {
                            Toast.makeText(Setup.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int arg0,
                                              Header[] arg1, byte[] arg2,
                                              Throwable arg3) {
                            Toast.makeText(Setup.this, "Problème de création de compte", Toast.LENGTH_SHORT).show();

                        }
                    });
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
                .setNegativeButton("Non", null)
                .show();
    }
}
