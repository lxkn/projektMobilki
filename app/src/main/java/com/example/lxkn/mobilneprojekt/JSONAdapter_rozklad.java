package com.example.lxkn.mobilneprojekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lxkn on 2017-01-13.
 */

public class JSONAdapter_rozklad extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;
    int position;
    //String baseURL = "http://api.football-data.org/v1/competitions/398/teams";

    public JSONAdapter_rozklad(Context mContext, LayoutInflater mInflater) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.mJsonArray = new JSONArray();
    }
    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JSONObject jsonObject = (JSONObject) getItem(position);
        String date = "", homeTeamName = "", awayTeamName= "";
        String path="";
        ViewHolder vh;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.rozklad_list_info,null);
            vh = new ViewHolder();
            vh.dateTextView = (TextView) convertView.findViewById(R.id.dateInfo);
            vh.homeTextView = (TextView) convertView.findViewById(R.id.homeTeamInfo);
            vh.awayTextView = (TextView) convertView.findViewById(R.id.awayTeamInfo);
            vh.pathImageView = (ImageView) convertView.findViewById(R.id.homeImage);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(jsonObject.has("date")){
            date= jsonObject.optString("date");
        }
        if(jsonObject.has("homeTeamName")){
            homeTeamName = jsonObject.optString(("homeTeamName"));
        }
        if(jsonObject.has("awayTeamName")){
            awayTeamName = jsonObject.optString("awayTeamName");
        }
        if(jsonObject.has("crestUrl"))
        {
            path = jsonObject.optString("crestUrl");
        }
        vh.dateTextView.setText(date);
        vh.homeTextView.setText(homeTeamName);
        vh.awayTextView.setText(awayTeamName);
        return convertView;
    }

    public void updateData(JSONArray jsonArray){
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
    private static class ViewHolder {
       // public ImageView ;
        public TextView dateTextView;
        public TextView homeTextView;
        public TextView awayTextView;
        public ImageView pathImageView;
    }
}
