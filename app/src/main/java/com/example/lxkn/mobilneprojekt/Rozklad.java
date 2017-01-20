package com.example.lxkn.mobilneprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by lxkn on 2017-01-09.
 */

public class Rozklad extends AppCompatActivity {
    private static final String rozkladURL = "http://api.football-data.org/v1/competitions/398/fixtures?matchday=1";
   // private static final String rozkladURL = "http://api.football-data.org/v1/competitions/398/fixtures?timeFrame=n2";
    JSONAdapter_rozklad mJSONADAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rozklad);
        ListView rozkladList = (ListView) findViewById(R.id.rozkladList);
        mJSONADAdapter = new JSONAdapter_rozklad(this,getLayoutInflater());
        rozkladList.setAdapter(mJSONADAdapter);
        showRozklad();
        rozkladList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JSONObject jsonObject = (JSONObject) mJSONADAdapter.getItem(i);
                 String id = jsonObject.optString("id","");

                Intent intent = new Intent(Rozklad.this, Rozklad_info.class);

// pack away the data about the cover
// into your Intent before you head out
                intent.putExtra("id",id);

// TODO: add any other data you'd like as Extras

// start the next Activity using your prepared Intent
                startActivity(intent);
            }
            });
    }

    private void showRozklad(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Auth-Token","be88a9efdae1495eb466febe9d79e639");
        client.get(rozkladURL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // Display a "Toast" message
                // to announce your success
                //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

                // 8. For now, just log results
                mJSONADAdapter.updateData(jsonObject.optJSONArray("fixtures"));
                //Log.d("omg android", jsonObject.toString());
                String coverID = jsonObject.optString("");

            }
            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                // Display a "Toast" message
                // to announce the failure
                Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();

                // Log error message
                // to help solve any problems
                Log.e("omg android", statusCode + " " + throwable.getMessage());
            }
        });
    }
    
}
