package com.example.lxkn.mobilneprojekt;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxkn on 2017-01-09.
 */

public class MenuListAdapter extends ArrayAdapter<MenuItem>{
    private Context context;
    int light;
    public MenuListAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context,0,menuItems);
    }

    public void setLight(int light)
    {
        this.light = light;
    }
    public int getLight(){
        return light;
    }

    public View getView(int position, View converView, ViewGroup parent) {
        MenuItem item = getItem(position);

        if (converView == null) {
            converView = LayoutInflater.from(getContext()).inflate(R.layout.menu, parent, false);
        }

        TextView tytul;
        tytul = (TextView) converView.findViewById(R.id.textViewNazwa);
        ImageView opis = (ImageView) converView.findViewById(R.id.imageViewMenu);
        tytul.setText(item.nazwa);
        if(light==1)
        {
            tytul.setTextColor(Color.BLACK);//android.R.color.black);
        }
        else
        {
            tytul.setTextColor(Color.WHITE);
        }
        opis.setImageResource(item.path);


        return converView;
    }

}
