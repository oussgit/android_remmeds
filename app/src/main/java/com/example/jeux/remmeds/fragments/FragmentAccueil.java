package com.example.jeux.remmeds.fragments;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.jeux.remmeds.classes.Profil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class FragmentAccueil extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();
    private static PriseAdapter mAdapter = new PriseAdapter(priseListe);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        final View acc = inflater.inflate(R.layout.fragment_accueil, container, false);

        mRecyclerView = acc.findViewById(R.id.prisesjour_recyclerView_layout_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem", "exception", e);
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return acc;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Accueil");
        if (priseListe.isEmpty()) {
        JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/compartment/list_com/" + MainActivity.getUserID());
        try {
            final Profil p = new Profil();
            JSONArray array = data.getJSONArray("compartment");
            generatePrises(array, p);
            refreshRecyclerAccueil();
        } catch (java.lang.NullPointerException e) {
            Log.e("NullJson", "Accueil" + e);
        } catch (org.json.JSONException e) {
            Log.e("JsonException", "Accueil" + e);
        }
        }
    }

    private void generatePrises(JSONArray array, Profil profil) {
        JSONObject object;
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        String dayname = dow.getDisplayName(TextStyle.FULL, Locale.FRANCE);
        dayname= dayname.substring(0,1).toUpperCase()+dayname.substring(1);
        for (int i = 0; i < 8; i++) {
            try {
                object = array.getJSONObject(i);
                String nommedicament = object.getString("drug_name");
                String dayperso = object.getString("days");
                String listpref = object.getString("list_pref");
                String heureperso = object.getString("perso_hour");
                int compnum = Integer.parseInt(object.getString("compartment_num"));
                int comp[] = {R.drawable.comp1,R.drawable.comp2,R.drawable.comp3,R.drawable.comp4,R.drawable.comp5,R.drawable.comp6,R.drawable.comp7,R.drawable.comp8};
                switch (object.getString("duration_text")) {
                    case "Jours":
                        String[] days = dayperso.split(",");
                        int check = 0;
                        int j = 0;
                        while (j < days.length && check < 1) {
                            if (days[j].equals(dayname)) {
                                check = 2;
                                String[] prefs = listpref.split(",");
                                for (String pref : prefs) {
                                    switch (pref) {
                                        case "Breakfast":
                                            Prise prise0 = new Prise(nommedicament, comp[compnum-1], profil.getBreakfastHour());
                                            break;
                                        case "Lunch":
                                            Prise prise1 = new Prise(nommedicament, comp[compnum-1], profil.getLunchHour());
                                            break;
                                        case "Dinner":
                                            Prise prise2 = new Prise(nommedicament, comp[compnum-1], profil.getDinnerHour());
                                            break;
                                        case "Bedtime":
                                            Prise prise3 = new Prise(nommedicament, comp[compnum-1], profil.getBedHour());;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            j++;
                        }
                        break;
                }
                if (!heureperso.equals("")) {
                    Prise prise4 = new Prise(nommedicament, comp[compnum-1], heureperso);
                }
            } catch (JSONException e) {
                Log.e("JSon Null ?", "generatePrise " + e);
            }
        }
    }

    public static void refreshRecyclerAccueil() {
        mAdapter.notifyDataSetChanged();
    }

    public static void addPrise(Prise prise){
        priseListe.add(prise);
    }

    public static void destroyRecyclerAccueil(){
        priseListe.clear();
        refreshRecyclerAccueil();
    }

}