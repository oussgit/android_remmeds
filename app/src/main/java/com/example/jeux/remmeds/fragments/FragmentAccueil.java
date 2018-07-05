package com.example.jeux.remmeds.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.classes.Prise;
import com.example.jeux.remmeds.classes.PriseAdapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentAccueil extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        final View acc = inflater.inflate(R.layout.fragment_accueil, container, false);
        PriseAdapter mAdapter;

        mRecyclerView = acc.findViewById(R.id.prisesjour_recyclerView_layout_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PriseAdapter(priseListe);

        Prise u = new Prise("Lexomil 500", R.drawable.comp2, "20h30");
        Prise d = new Prise("Doliprane", R.drawable.comp3, "15h00");
        Prise y = new Prise("Doliprane", R.drawable.comp3, "12h00");
        Prise b = new Prise("Smecta", R.drawable.comp5, "11h00");
        Prise a = new Prise("Paracétamol", R.drawable.comp8, "9h00");

        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem", "exception", e);
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (priseListe.isEmpty()) {
            priseListe.add(a);
            priseListe.add(b);
            priseListe.add(y);
            priseListe.add(d);
            priseListe.add(u);
            mAdapter.notifyDataSetChanged();

        }
        return acc;
    }
        @Override
        public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            //you can set the title for your toolbar here for different fragments different titles
            getActivity().setTitle("Accueil");
        }
    }