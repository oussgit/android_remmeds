package com.example.jeux.remmeds.fragments;

import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.HistoriqueAdapter;
import com.example.jeux.remmeds.classes.Prise;
import com.example.jeux.remmeds.classes.PriseAdapter;
import com.example.jeux.remmeds.classes.Profil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.jeux.remmeds.fragments.FragmentAccueil.refreshRecyclerAccueil;

public class FragmentHistorique extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        final View acc = inflater.inflate(R.layout.fragment_historique, container, false);
        RecyclerView mRecyclerView;
        mRecyclerView = acc.findViewById(R.id.evenements_recyclerview_layout_historique);
        HistoriqueAdapter mAdapter;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HistoriqueAdapter(priseListe);
        mRecyclerView.setAdapter(mAdapter);

        JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/historic/list_historic/" + MainActivity.getUserID());
        try {
            JSONArray array = data.getJSONArray("historic");
            generateHistoric(array);
            if (priseListe.isEmpty()) {
                generateHistoric(array);
                mAdapter.notifyDataSetChanged();
            }
        } catch (java.lang.NullPointerException e) {
            Log.e("NullJson", "Accueil" + e);
        } catch (org.json.JSONException e) {
            Log.e("JsonException", "catched " + e);
        }

        try
        {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (
                Exception e)
        {
            Log.e("mRecyclerView.addDeco", "exception", e);
        }

        return acc;
    }

    private void generateHistoric(JSONArray array) {
        String drugName;
        String day;
        String hour;
        String numComp;
        String isTaken;
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                drugName = object.getString("drug_name");
                day = object.getString("day");
                hour = object.getString("hour");
                numComp = object.getString("num_comp");
                isTaken = object.getString("respected");

                Prise prise = new Prise(drugName, getPicture(numComp), hour, day, isTaken);
                priseListe.add(prise);
            } catch (JSONException e) {
                Log.e("Exception Catched", "Fragment Historique " + e);
            } catch (java.lang.NullPointerException e) {
                Log.e("JSON Null", "Fragment Historique " + e);
            }
        }
    }

    private int getPicture(String numComp) {
        int compPicture;
        switch (numComp) {
            case "1":
                compPicture = R.drawable.comp1;
                return compPicture;
            case "2":
                compPicture = R.drawable.comp2;
                return compPicture;
            case "3":
                compPicture = R.drawable.comp3;
                return compPicture;
            case "4":
                compPicture = R.drawable.comp4;
                return compPicture;
            case "5":
                compPicture = R.drawable.comp5;
                return compPicture;
            case "6":
                compPicture = R.drawable.comp6;
                return compPicture;
            case "7":
                compPicture = R.drawable.comp7;
                return compPicture;
            case "8":
                compPicture = R.drawable.comp8;
                return compPicture;
            default:
                compPicture = R.drawable.comp8;
                return compPicture;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Historique");

    }
}