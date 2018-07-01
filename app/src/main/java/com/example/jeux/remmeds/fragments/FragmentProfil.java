package com.example.jeux.remmeds.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.support.annotation.NonNull;
import com.example.jeux.remmeds.R;

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

        final EditText field_breakfast_profil = view.findViewById(R.id.breakfast_editText_layout_profil);
        final EditText field_lunch_profil = view.findViewById(R.id.lunch_edittext_layout_profil);
        final EditText field_dinner_profil = view.findViewById(R.id.dinner_edittext_layout_profil);
        final EditText field_bedtime_profil = view.findViewById(R.id.bedtime_edittext_layout_profil);

        get_formatted_hour(field_breakfast_profil);
        get_formatted_hour(field_lunch_profil);
        get_formatted_hour(field_dinner_profil);
        get_formatted_hour(field_bedtime_profil);
    }
}