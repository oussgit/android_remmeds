package com.example.jeux.remmeds.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeux.remmeds.activities.Ajoutcontact;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.Contact;
import com.example.jeux.remmeds.classes.ContactAdapter;
import com.example.jeux.remmeds.R;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class FragmentRepertoire extends Fragment {
    private static List<Contact> contactList = new ArrayList<>();
    private static ContactAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        final RecyclerView mRecyclerView;
        final View rep = inflater.inflate(R.layout.fragment_repertoire, container, false);
        Button addButton;

        mRecyclerView = rep.findViewById(R.id.rep_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ContactAdapter(contactList);

        if (contactList.isEmpty()) {
            fillRecycler("http://212.73.217.202:15020/repertory/list_repertory/6");
        }

        addButton = rep.findViewById(R.id.ajouter_button_layout_repertoire);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Ajoutcontact.class);
                startActivity(i);
            }
        });


        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return rep;
    }

    public void fillRecycler(String str) {

        JSONObject data = MainActivity.getDoInBackground(str);
        JSONArray array = null;
        try {
            array = data.getJSONArray("repertory_contact");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < array.length(); i++) {
            Contact a = new Contact();
            try {
                a.setNom(array.getJSONObject(i).getString("lastname"));
                a.setPrenom(array.getJSONObject(i).getString("firstname"));
                a.setAdresse(array.getJSONObject(i).getString("mail"));
                a.setNumero(array.getJSONObject(i).getString("phonenumber"));
                contactList.add(a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public static void addItem(String nom, String prenom, String adresse, String numero) {
        Contact contact = new Contact(nom, prenom, adresse, numero);
        contactList.add(contact);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        try {
            getActivity().setTitle("Repertoire");
        } catch (Exception e) {
            Log.e("getActivity.setTitle", "exception", e);
        }
    }


}