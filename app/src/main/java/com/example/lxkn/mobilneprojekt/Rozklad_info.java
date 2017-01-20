package com.example.lxkn.mobilneprojekt;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Rozklad_info extends AppCompatActivity {

    private static final String BASE_URL = "http://api.football-data.org/v1/competitions/398/teams";
    private static final String BASE_2 = "http://api.football-data.org/v1/fixtures/";
    //String id = this.getIntent().getExtras().getString("id");
    JSONAdapter_rozklad mJSONADAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_rozklad_info);
        // Enable the "Up" button for more navigation options
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access the imageview from XML
        TextView homeTextView = (TextView) findViewById(R.id.homeText);
        TextView awayTextView = (TextView) findViewById(R.id.awayText);
        ImageView homeimgView = (ImageView) findViewById(R.id.homeImage);
        ImageView awayimgView = (ImageView) findViewById(R.id.awayImage);
        homeTextView.setText(id);
    }
}
