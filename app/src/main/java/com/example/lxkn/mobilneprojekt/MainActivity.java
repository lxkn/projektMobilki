package com.example.lxkn.mobilneprojekt;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ListView menuListView;
    SensorManager manager;
    final List<MenuItem> menuList = new ArrayList();
    MenuListAdapter adapter;
    public Sensor sensor;
    public ListView getMenuList() {
        return menuListView;
    }


    public void setMenuList(ListView menuListView) {
        this.menuListView = menuListView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Bundle extras = getIntent().getExtras();
        //typ=extras.getInt("SENSOR_TYPE");
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);


        MenuItem item = new MenuItem(getString(R.string.results), R.drawable.ic_action_name);
        MenuItem item2 = new MenuItem(getString(R.string.schedule), R.drawable.ic_action_name);
        MenuItem item3 = new MenuItem(getString(R.string.table), R.drawable.ic_action_name);
        MenuItem item4 = new MenuItem(getString(R.string.timetable), R.mipmap.terminarz2);
        menuList.add(item);
        menuList.add(item2);
        menuList.add(item3);
        menuList.add(item4);
        ArrayList<MenuItem> test = new ArrayList<MenuItem>(menuList);
        adapter = new MenuListAdapter(this, test);
        menuListView = (ListView) findViewById(R.id.menuList);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), Wyniki.class);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), Rozklad.class);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), Tabela.class);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), Terminarz.class);
                        break;
                    default:
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }


            }
        });
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event) {


            //TextView wartosc = (TextView) findViewById(R.id.wartosc);
            //wartosc.setText("Natezenie Swiatła: " + event.values[0]);
            if(event.values[0]<20){

                menuListView.setBackgroundColor(getResources().getColor(android.R.color.black));
                //wartosc.setText("Natezenie Swiatła: " + event.values[0]);
                adapter.setLight(2);
                adapter.notifyDataSetChanged();
            }
            else
            {
                menuListView.setBackgroundColor(getResources().getColor(android.R.color.white));
               // adapter.setTytul(android.R.color.black,adapter.tytul);
                adapter.setLight(1);
                adapter.notifyDataSetChanged();
            }



    }
    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        manager.registerListener(this, sensor , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        manager.unregisterListener(this);
    }

}
