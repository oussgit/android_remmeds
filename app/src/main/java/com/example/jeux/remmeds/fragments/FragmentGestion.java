package com.example.jeux.remmeds.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.Compartiment;

public class FragmentGestion extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //return inflater.inflate(R.layout.fragment_gestion, container, false);

        final View ges = inflater.inflate(R.layout.fragment_gestion, container, false);

        Button comp1;
        Button comp2;
        Button comp3;
        Button comp4;
        Button comp5;
        Button comp6;
        Button comp7;
        Button comp8;

        comp1 = ges.findViewById(R.id.compartiment1_button_layout_pilulier);
        comp2 = ges.findViewById(R.id.compartiment2_button_layout_pilulier);
        comp3 = ges.findViewById(R.id.compartiment3_button_layout_pilulier);
        comp4 = ges.findViewById(R.id.compartiment4_button_layout_pilulier);
        comp5 = ges.findViewById(R.id.compartiment5_button_layout_pilulier);
        comp6 = ges.findViewById(R.id.compartiment6_button_layout_pilulier);
        comp7 = ges.findViewById(R.id.compartiment7_button_layout_pilulier);
        comp8 = ges.findViewById(R.id.compartiment8_button_layout_pilulier);

        comp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        comp8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Compartiment.class);
                startActivity(i);
                }
        });
        return ges;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Gestion des Sections");
    }
}
