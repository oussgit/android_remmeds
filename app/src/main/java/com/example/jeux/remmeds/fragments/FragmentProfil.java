package com.example.jeux.remmeds.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.Authentification;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.Profil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;

public class FragmentProfil extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_profil, container, false);


    }

    private void get_formatted_hour(final EditText which_field) {
        //Launch a time picker instead of keyboard on clock field
        which_field.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar my_current_time = Calendar.getInstance();
                int hour = my_current_time.get(Calendar.HOUR_OF_DAY);
                int minute = my_current_time.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        DecimalFormat add_zero = new DecimalFormat("00");
                        which_field.setText(add_zero.format(selectedHour) + ":" + add_zero.format(selectedMinute)); //NOSONAR
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Sélectionner heure");
                mTimePicker.show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Profil");
        final String userID = MainActivity.getUserID();
        String profilURL = "http://212.73.217.202:15020/raspberry/get_user/" + userID;
        JSONObject data = MainActivity.getDoInBackground(profilURL);
        final Profil user = new Profil();
        JSONArray array = new JSONArray();
        try {
            array = data.getJSONArray("user");
        } catch (JSONException e) {
            Log.e("arrayRecycler", "Exception catched" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("arrayRecycler", "NULL JSON" + e);
        }
        for (int i = 0; i < array.length(); i++) {
            try {
                user.setLastname(array.getJSONObject(i).getString("lastname"));
                user.setFirstname(array.getJSONObject(i).getString("firstname"));
                user.setMail(array.getJSONObject(i).getString("mail"));
                user.setBreakfastHour(array.getJSONObject(i).getString("pref_breakfast"));
                user.setLunchHour(array.getJSONObject(i).getString("pref_lunch"));
                user.setDinnerHour(array.getJSONObject(i).getString("pref_dinner"));
                user.setBedHour(array.getJSONObject(i).getString("pref_bedtime"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final EditText field_lastname_profil = view.findViewById(R.id.nom_edittext_layout_profil);
        field_lastname_profil.setText(user.getLastname());
        final EditText field_firstname_profil = view.findViewById(R.id.prenom_edittext_layout_profil);
        field_firstname_profil.setText(user.getFirstname());
        final EditText field_breakfast_profil = view.findViewById(R.id.breakfast_editText_layout_profil);
        field_breakfast_profil.setText(user.getBreakfastHour());
        final EditText field_lunch_profil = view.findViewById(R.id.lunch_edittext_layout_profil);
        field_lunch_profil.setText(user.getLunchHour());
        final EditText field_dinner_profil = view.findViewById(R.id.dinner_edittext_layout_profil);
        field_dinner_profil.setText(user.getDinnerHour());
        final EditText field_bedtime_profil = view.findViewById(R.id.bedtime_edittext_layout_profil);
        field_bedtime_profil.setText(user.getBedHour());

        get_formatted_hour(field_breakfast_profil);
        get_formatted_hour(field_lunch_profil);
        get_formatted_hour(field_dinner_profil);
        get_formatted_hour(field_bedtime_profil);

        Button enregistrer_button = view.findViewById(R.id.enregistrer_button_layout_profil);
        enregistrer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_firstname = field_firstname_profil.getText().toString();
                String new_lastname = field_lastname_profil.getText().toString();
                String new_bf = field_breakfast_profil.getText().toString();
                String new_lun = field_lunch_profil.getText().toString();
                String new_din = field_dinner_profil.getText().toString();
                String new_bed = field_bedtime_profil.getText().toString();
                String mail = user.getMail();
                String urlUpdateProfil = "http://212.73.217.202:15020/user/update_account/" + userID + "&" + mail + "&" + new_lastname + "&" + new_firstname + "&" + new_bf + "&" + new_lun + "&" + new_din + "&" + new_bed;
                MainActivity.postDoInBackground(urlUpdateProfil);
                Toast.makeText(getActivity(), "Profil mis à jour", Toast.LENGTH_SHORT).show();
            }
        });
        Button changer_mdp_button = view.findViewById(R.id.password_button_layout_profil);
        changer_mdp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}