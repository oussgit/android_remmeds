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
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.Prise;
import com.example.jeux.remmeds.classes.PriseAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentAccueil extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();
    private static String dayperso;
    private static String listpref;
    private static String durationtext;
    private static String durationnumber;
    private static String drugname;
    private static String notes;
    private static String compid;
    private static String heureperso;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        final View acc = inflater.inflate(R.layout.fragment_accueil, container, false);
        PriseAdapter mAdapter;

        mRecyclerView = acc.findViewById(R.id.prisesjour_recyclerView_layout_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PriseAdapter(priseListe);

        Prise u = new Prise("Tramadol", R.drawable.comp2, "20h00");
        Prise d = new Prise("Paracetamol", R.drawable.comp1, "15h00");
        Prise a = new Prise("Tramadol", R.drawable.comp2, "9h00");

        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem", "exception", e);
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (priseListe.isEmpty()) {
            priseListe.add(a);
            priseListe.add(d);
            priseListe.add(u);
            mAdapter.notifyDataSetChanged();

        }

        JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/compartment/list_com/" + MainActivity.getUserID());
        try {
            JSONArray array = data.getJSONArray("compartment");
            generateListe(array);
        } catch (java.lang.NullPointerException e) {
            Log.e("NullJson","Accueil"+e);
        }catch (org.json.JSONException e) {
            Log.e("JsonException","Accueil"+e);
        }


        return acc;
    }
        private void generateListe(JSONArray array){

        }
        @Override
        public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            //you can set the title for your toolbar here for different fragments different titles
            getActivity().setTitle("Accueil");
        }
    }