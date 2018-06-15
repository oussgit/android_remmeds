package com.example.jeux.remmeds.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jeux.remmeds.fragments.FragmentRepertoire;
import com.example.jeux.remmeds.fragments.FragmentAccueil;
import com.example.jeux.remmeds.fragments.FragmentConfiguration;
import com.example.jeux.remmeds.fragments.FragmentHistorique;
import com.example.jeux.remmeds.fragments.FragmentProfil;
import com.example.jeux.remmeds.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Affiche l'accueil en premier écran
        displayFragment(R.id.fragment_accueuil);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displayFragment(item.getItemId());
        //make this method blank
        return true;
    }

    private void displayFragment(int itemId) {
        //creation du fragment
        Fragment fragment;
        //initialisation du fragment selectionné
        switch (itemId) {
            case R.id.nav_acueuil:
                fragment = new FragmentAccueil();
                break;
            case R.id.nav_configuration:
                fragment = new FragmentConfiguration();
                break;
            case R.id.nav_historique:
                fragment = new FragmentHistorique();
                break;
            case R.id.nav_profil:
                fragment = new FragmentProfil();
                break;
            case R.id.nav_repertoire:
                fragment = new FragmentRepertoire();
                break;
            default:
                fragment = new FragmentAccueil();
        }

        //Remplacement du fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
