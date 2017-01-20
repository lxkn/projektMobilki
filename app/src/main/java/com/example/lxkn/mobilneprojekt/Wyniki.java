package com.example.lxkn.mobilneprojekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

public class Wyniki extends AppCompatActivity {
    String wynikiURL = "http://api.football-data.org/v1/competitions/398/fixtures";
    JSONAdapter_wyniki mJSONADAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyniki);
        ListView rozkladList = (ListView) findViewById(R.id.wynikList);
        mJSONADAdapter = new JSONAdapter_wyniki(this,getLayoutInflater());
        rozkladList.setAdapter(mJSONADAdapter);
        showWyniki();
    }
    private void showWyniki(){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(wynikiURL, new JsonHttpResponseHandler(){
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
