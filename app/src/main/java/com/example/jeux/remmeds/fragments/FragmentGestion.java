package com.example.jeux.remmeds.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.Compartiment;
import com.example.jeux.remmeds.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentGestion extends Fragment {

    Button comp1;
    Button comp2;
    Button comp3;
    Button comp4;
    Button comp5;
    Button comp6;
    Button comp7;
    Button comp8;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //return inflater.inflate(R.layout.fragment_gestion, container, false);

        final View ges = inflater.inflate(R.layout.fragment_gestion, container, false);

        comp1 = ges.findViewById(R.id.compartiment1_button_layout_pilulier);
        comp2 = ges.findViewById(R.id.compartiment2_button_layout_pilulier);
        comp3 = ges.findViewById(R.id.compartiment3_button_layout_pilulier);
        comp4 = ges.findViewById(R.id.compartiment4_button_layout_pilulier);
        comp5 = ges.findViewById(R.id.compartiment5_button_layout_pilulier);
        comp6 = ges.findViewById(R.id.compartiment6_button_layout_pilulier);
        comp7 = ges.findViewById(R.id.compartiment7_button_layout_pilulier);
        comp8 = ges.findViewById(R.id.compartiment8_button_layout_pilulier);

        displayCompartement();
        return ges;
    }

    private void displayCompartement() {
        JSONArray array = CreateArray("http://212.73.217.202:15020/compartment/list_com/");
        if (array != null) {
            try {
                putItents(comp1, array.getJSONObject(0));
                putItents(comp2, array.getJSONObject(1));
                putItents(comp3, array.getJSONObject(2));
                putItents(comp4, array.getJSONObject(3));
                putItents(comp5, array.getJSONObject(4));
                putItents(comp6, array.getJSONObject(5));
                putItents(comp7, array.getJSONObject(6));
                putItents(comp8, array.getJSONObject(7));
            } catch (JSONException e) {
                Log.e("NUL JSON OBJECT","Fragmentgestion.display");
            }
        }
    }

    private JSONArray CreateArray(String url) {
        JSONObject data = MainActivity.getDoInBackground(url + MainActivity.getUserID());
        try {
            JSONArray array = data.getJSONArray("compartment");
            return array;
        } catch (JSONException e) {
            Log.e("arrayRecycler", "Exception catched" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("arrayRecycler", "NULL JSON" + e);
        }
        return null;
    }

    private void putItents(Button comp, final JSONObject object) {
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                try {
                    i.putExtra("duration_number", object.getString("duration_number"));
                    i.putExtra("check_perso_hour", object.getString("check_perso_hour"));
                    i.putExtra("drug_name", object.getString("drug_name"));
                    i.putExtra("frequency", object.getString("frequency"));
                    i.putExtra("compartment_num", object.getString("compartment_num"));
                    i.putExtra("list_pref", object.getString("list_pref"));
                    i.putExtra("perso_hour", object.getString("perso_hour"));
                    i.putExtra("duration_text", object.getString("duration_text"));
                    i.putExtra("compartment_id", object.getString("compartment_id"));
                    i.putExtra("days", object.getString("days"));
                    i.putExtra("note", object.getString("note"));
                } catch (JSONException e) {
                    Log.e("JSON IS NULL", "putItents");
                }
                startActivity(i);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Gestion des Sections");
    }
}
