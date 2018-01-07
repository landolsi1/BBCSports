package com.appsolute.rami.bbcsports.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsolute.rami.bbcsports.Entities.Sport;
import com.appsolute.rami.bbcsports.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rami on 1/4/2018.
 */

public class SportAdapter extends ArrayAdapter<Sport> {

    public static class ViewHolder{
        TextView txtTitle;
        TextView txtDate;
        TextView txtDescription;
        ImageView imgSport;
    }

    public SportAdapter(@NonNull Context context, List<Sport> cupidons) {
        super(context, 0, cupidons);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sport sport = getItem(position);
        ViewHolder vh;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_sport,parent,  false);

            vh = new ViewHolder();
            vh.txtTitle = convertView.findViewById(R.id.txtTitle);
            vh.txtDescription = convertView.findViewById(R.id.txtDescription);
            vh.txtDate = convertView.findViewById(R.id.txtDate);
            vh.imgSport = convertView.findViewById(R.id.imgSportt);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.txtTitle.setText(sport.getTitle()+" - Source: "+sport.getAuthor());
        vh.txtDescription.setText(sport.getDescription());
        vh.txtDate.setText(sport.getPublishedAt());

        Picasso.with(getContext()).load(sport.getUrlToImage()).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round)
                .into(vh.imgSport, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
        //vh.imgSport.setImageResource(sport.getUrlToImage());

        return convertView;
    }
}
