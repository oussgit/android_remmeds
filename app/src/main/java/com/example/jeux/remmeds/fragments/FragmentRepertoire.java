package com.example.jeux.remmeds.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.Ajoutcontact;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.Contact;
import com.example.jeux.remmeds.classes.ContactAdapter;
import com.example.jeux.remmeds.classes.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentRepertoire extends Fragment {
    private static List<Contact> contactList = new ArrayList<>();
    private static ContactAdapter mAdapter = new ContactAdapter(contactList);
    private static Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        mContext = this.getContext();
        final RecyclerView mRecyclerView;
        final View rep = inflater.inflate(R.layout.fragment_repertoire, container, false);
        Button addButton;

        mRecyclerView = rep.findViewById(R.id.rep_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
                        i.putExtra("ContactId", contact.getIdContact());
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

    public static void fillRecyclerRep(String id) {
        JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/contact/list_contact/" + id);
        JSONArray array = new JSONArray();
        try {
            array = data.getJSONArray("contact");
            for (int i = 0; i < array.length(); i++) {
                Contact a = new Contact(array,i);
                addContact(a);
            }
            refreshRecyclerRep();
        } catch (JSONException e) {
            Log.e("CreationContact", "Erreur" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("CreationContact", "NULL JSON" + e);
            Toast.makeText(getRepContext(), "Erreur de connexion, veuillez re-essayer", Toast.LENGTH_SHORT).show();
        }
    }

    public static Context getRepContext(){
        return mContext;
    }

    public static void addContact(Contact contact) {
        contactList.add(contact);
    }

    public static Contact getContact(int contactPos) {
        return contactList.get(contactPos);
    }

    public static void refreshRecyclerRep() {
        mAdapter.notifyDataSetChanged();
    }

    public static void removeItem(int position) {
        contactList.remove(position);
        refreshRecyclerRep();
    }

    public static void emptyContact() {
        contactList.clear();
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