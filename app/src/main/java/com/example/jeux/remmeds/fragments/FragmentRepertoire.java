package com.example.jeux.remmeds.fragments;

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
import com.example.jeux.remmeds.classes.Contact;
import com.example.jeux.remmeds.classes.ContactAdapter;
import com.example.jeux.remmeds.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class FragmentRepertoire extends Fragment {
    private List<Contact> contactList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        RecyclerView mRecyclerView;
        ContactAdapter mAdapter;
        View rep = inflater.inflate(R.layout.fragment_repertoire, container, false);
        mRecyclerView = rep.findViewById(R.id.rep_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ContactAdapter(contactList);

        Contact contact1 = new Contact("Callas", "Oussama", "4 allée des Merisiers", "0646744246");
        Contact contact2 = new Contact("Foucher", "Mathieu", "4 allée du Swag", "064457869");
        Contact contact3 = new Contact("Haddouche", "Koceila", "63 rue des la marche", "0644796246");
        Contact contact4 = new Contact("Dumont", "Adrien", "69 rue du Buffet Gargantuesque", "0142744246");
        Contact contact6 = new Contact("Lelouche", "Giles", "159 rue Olive", "0646744246");
        Contact contact5 = new Contact("LeCoto", "Anthony", "53 boulevard Marceau", "0425869574");
        Contact contact7 = new Contact("Pierre", "Jean", "4 allée des Cerisiers", "0646544246");
        contactList.add(contact1);
        contactList.add(contact2);
        contactList.add(contact3);
        contactList.add(contact4);
        contactList.add(contact5);
        contactList.add(contact6);
        contactList.add(contact7);

        mAdapter.notifyDataSetChanged();
        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem","exception",e);
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return rep;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        try {
            getActivity().setTitle("Repertoire");
        } catch (Exception e) {
            Log.e("getActivity.setTitle","exception",e);
        }
    }
}