package com.example.jeux.remmeds.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.jeux.remmeds.R;

public class Refresh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        //ImageView logorefresh = findViewById(R.id.refresh_imageview_layout_refresh);
        //logorefresh.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent i = new Intent(v.getContext(), Refresh.class);
        //        startActivity(i);
        //    }
        //});
    }
}
