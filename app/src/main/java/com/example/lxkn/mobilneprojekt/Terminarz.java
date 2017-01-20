package com.example.lxkn.mobilneprojekt;

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

public class Terminarz extends AppCompatActivity {
    String URL = "http://api.football-data.org/v1/competitions/398/fixtures?matchday=2";
    // private static final String rozkladURL = "http://api.football-data.org/v1/competitions/398/fixtures?timeFrame=n2";
    JSONAdapter_rozklad mJSONADAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminarz);
        ListView rozkladList = (ListView) findViewById(R.id.terminarzList);
        mJSONADAdapter = new JSONAdapter_rozklad(this, getLayoutInflater());
        rozkladList.setAdapter(mJSONADAdapter);
        showRozklad();
    }
    private void showRozklad(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Auth-Token","be88a9efdae1495eb466febe9d79e639");
        client.get(URL, new JsonHttpResponseHandler(){
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
