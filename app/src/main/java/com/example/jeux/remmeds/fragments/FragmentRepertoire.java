package com.example.jeux.remmeds.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeux.remmeds.activities.Ajoutcontact;
import com.example.jeux.remmeds.classes.RecyclerItemClickListener;
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
    public static List<Contact> contactList = new ArrayList<>();
    public static ContactAdapter mAdapter = new ContactAdapter(contactList);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        final RecyclerView mRecyclerView;
        final View rep = inflater.inflate(R.layout.fragment_repertoire, container, false);
        Button addButton;

        mRecyclerView = rep.findViewById(R.id.rep_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Contact contact = contactList.get(position);
                        Intent i = new Intent(view.getContext(), Ajoutcontact.class);
                        i.putExtra("ContactNom", contact.getNom());
                        i.putExtra("ContactPrenom", contact.getPrenom());
                        i.putExtra("ContactMail", contact.getMailContact());
                        i.putExtra("ContactMailCheck", contact.getMailCheck());
                        i.putExtra("ContactSmsCheck", contact.getSmsCheck());
                        i.putExtra("ContactNum", contact.getNumero());
                        i.putExtra("ContactNote", contact.getNoteContact());
                        i.putExtra("ContactPos", position);
                        i.putExtra("ContactId",contact.getIdContact());
                        startActivity(i);
                    }
                })
        );
        if (contactList.isEmpty()) {
            fillRecyclerRep(MainActivity.getUserID());
        }
        addButton = rep.findViewById(R.id.ajouter_button_layout_repertoire);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Ajoutcontact.class);
                startActivity(i);
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return rep;
    }

    public void fillRecyclerRep(String id) {
        JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/contact/list_contact/" + id);
        JSONArray array = new JSONArray();
        try {
            array = data.getJSONArray("contact");
        } catch (JSONException e) {
            Log.e("arrayRecycler", "Exception catched" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("arrayRecycler", "NULL JSON" + e);
        }
        for (int i = 0; i < array.length(); i++) {
            Contact a = new Contact();
            try {
                a.setNom(array.getJSONObject(i).getString("lastname"));
                a.setPrenom(array.getJSONObject(i).getString("firstname"));
                a.setMailContact(array.getJSONObject(i).getString("mail"));
                a.setNumero(array.getJSONObject(i).getString("phonenumber"));
                a.setMailCheck(array.getJSONObject(i).getString("chx_mail"));
                a.setSmsCheck(array.getJSONObject(i).getString("chx_sms"));
                a.setNoteContact(array.getJSONObject(i).getString("note"));
                a.setIdContact(array.getJSONObject(i).getString("contact_id"));
                contactList.add(a);
            } catch (JSONException e) {
                Log.e("CreationContact", "NULL JSON" + e);
            }
        }
        refreshRecyclerRep();
    }

    public static void refreshRecyclerRep() {
        mAdapter.notifyDataSetChanged();
    }

    public static void changeItemRecyclerRep(int position){
        mAdapter.notifyItemChanged(position);
    }

    public static void addItem(String nom, String prenom, String adresse, String numero, String mailCheck, String smsCheck, String note, String id) {
        Contact contact = new Contact(nom, prenom, adresse, numero, mailCheck, smsCheck, note, id);

        contactList.add(contact);
        refreshRecyclerRep();
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