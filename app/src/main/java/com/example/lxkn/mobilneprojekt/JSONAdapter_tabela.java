package com.example.lxkn.mobilneprojekt;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.sip.SipSession;
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
 * Created by lxkn on 2017-01-19.
 */

public class JSONAdapter_tabela extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;
    int position;
    //String baseURL = "http://api.football-data.org/v1/competitions/398/teams";

    public JSONAdapter_tabela(Context mContext, LayoutInflater mInflater) {
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
        //JSONArray jsonArray;

        String rank="",team="",points="",goals="",goalsAgainst="",crestURI="";
        ViewHolder3 vh;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.activity_tabela_info,null);
            vh = new ViewHolder3();
            vh.rank = (TextView) convertView.findViewById(R.id.rank);
            vh.team = (TextView) convertView.findViewById(R.id.team);
            vh.points = (TextView) convertView.findViewById(R.id.points);
            vh.goals = (TextView) convertView.findViewById(R.id.goals);
            vh.goalsAgainst = (TextView) convertView.findViewById(R.id.goalsAgainst);
            vh.teamAvatar = (ImageView) convertView.findViewById(R.id.teamAvatar);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder3) convertView.getTag();
        }
        if(jsonObject.has("position")){
            rank="Rank: "+jsonObject.opt("position");
        }
        if(jsonObject.has("teamName")){
            team="Team: "+jsonObject.optString("teamName");
        }
        if(jsonObject.has("points")){
            points="Points: "+jsonObject.optString("points");
        }
        if(jsonObject.has("goals")){
            goals="Goals: "+jsonObject.optString("goals");
        }
        if(jsonObject.has("goalsAgainst")){
            goalsAgainst="GoalsAgainst: "+jsonObject.optString("goalsAgainst");
        }
        if(jsonObject.has("crestURI")){
            crestURI = jsonObject.optString("crestURI");
            Picasso.with(mContext)
                    .load(crestURI)
                    .placeholder(R.drawable.obraz)
                    .into(vh.teamAvatar);
        }
        vh.rank.setText(rank);
        vh.team.setText(team);
        vh.points.setText(points);
        vh.goals.setText(goals);
        vh.goalsAgainst.setText(goalsAgainst);
        return convertView;
    }

    public void updateData(JSONArray jsonArray){
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
    private static class ViewHolder3 {
        public TextView rank,team,points,goals,goalsAgainst;
        public ImageView teamAvatar;
    }
}