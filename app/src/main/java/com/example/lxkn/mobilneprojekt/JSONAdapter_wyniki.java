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
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lxkn on 2017-01-13.
 */

public class JSONAdapter_wyniki extends BaseAdapter{
        Context mContext;
        LayoutInflater mInflater;
        JSONArray mJsonArray;
        int position;
        //String baseURL = "http://api.football-data.org/v1/competitions/398/teams";

        public JSONAdapter_wyniki(Context mContext, LayoutInflater mInflater) {
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
           // JSONArray jsonArray = (JSONArray) getItem(position);

            String scoreHome = "", awayTeamName="", scoreAway="";
            ViewHolder2 vh;
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.wyniki,null);
                vh = new ViewHolder2();
                vh.score1 = (TextView) convertView.findViewById(R.id.score1);
                vh.score2 = (TextView) convertView.findViewById(R.id.score2);
                vh.awayScoreText = (TextView) convertView.findViewById(R.id.awayScoreText);

                convertView.setTag(vh);
            }
            else {
                vh = (ViewHolder2) convertView.getTag();
            }

                if(jsonObject.has("awayTeamName")){
                    awayTeamName = jsonObject.optString("awayTeamName");

                }
                if(jsonObject.has("result"))
                {

                    scoreHome = jsonObject.optString("result");
                    //scoreAway = jsonArray.optString(1,"goalsAwayTeam");
                }
                if(jsonObject.has("homeTeamName")) {

                    scoreAway = jsonObject.optString("homeTeamName");

                }
            vh.score1.setText(scoreHome);
            vh.score2.setText(" ");
            vh.awayScoreText.setText(awayTeamName + " vs " +scoreAway);
            return convertView;
        }

        public void updateData(JSONArray jsonArray){
            mJsonArray = jsonArray;
            notifyDataSetChanged();
        }
        private static class ViewHolder2 {
            public TextView score1,score2,awayScoreText;
        }
}


