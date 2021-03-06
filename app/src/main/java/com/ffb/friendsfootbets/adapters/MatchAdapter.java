package com.ffb.friendsfootbets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffb.friendsfootbets.R;
import com.ffb.friendsfootbets.models.Match;
import com.ffb.friendsfootbets.models.Tournament;
import com.ffb.friendsfootbets.models.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Younes and Mehdi on 12/05/2017.
 */
// Adapted from : http://tutos-android-france.com/listview-afficher-une-liste-delements/
public class MatchAdapter extends ArrayAdapter<Match> {
    private User currentUser;

    //Tournaments est la liste des models à afficher
    public MatchAdapter(Context context, ArrayList<Match> matches, User user) {
        super(context, 0, matches);
        currentUser = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_fixtures_to_add,parent, false);
        }

        TournamentViewHolder viewHolder = (TournamentViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TournamentViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.homeTeam = (TextView) convertView.findViewById(R.id.hteam);
            viewHolder.awayTeam = (TextView) convertView.findViewById(R.id.ateam);
            viewHolder.hour = (TextView) convertView.findViewById(R.id.heure);
            //viewHolder.state = (ImageView) convertView.findViewById(R.id.state);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tournament> Tournaments
        Match match = getItem(position);
        //il ne reste plus qu'à remplir notre vue
        /*if(currentUser.getBets().keySet().contains(match.getMatchId())){
            viewHolder.state.setImageResource(R.drawable.ic_turned_in);
        }else{
            viewHolder.state.setImageResource(R.drawable.ic_turned_in_not);
        }
        */
        viewHolder.date.setText(match.getMatchDate());
        viewHolder.hour.setText(match.getMatchHour());
        viewHolder.homeTeam.setText(match.getNameHomeTeam());
        viewHolder.awayTeam.setText(match.getNameAwayTeam());

        return convertView;
    }

    private class TournamentViewHolder{
        protected TextView date;
        protected TextView homeTeam;
        protected TextView awayTeam;
        protected TextView hour;
        protected ImageView state;

    }


}