package com.appsolute.rami.bbcsports;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rami on 1/4/2018.
 */

public class InfosFragment  extends Fragment {

    public static InfosFragment newInstance() {

        return new InfosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_infos, container, false);

       TextView mTextMessage = view.findViewById(R.id.fragment_infos);
        mTextMessage.setText(R.string.title_more);

        TextView mTextMessagefav = view.findViewById(R.id.favoris);
        mTextMessagefav.setText("Ajout au favoris");

        TextView mTextMessageFbi = view.findViewById(R.id.fbi);
        mTextMessageFbi.setText("Dans la rubrique Favoris l'appuie sur un element permet d'afficher ses détail. \n Un appuie long permet de supprimer l'élément en question. \n Une connexion est nécessaire sinon l'application se ferme automatiquement au bout de 5 seconde... \n ");

        TextView mTextMessageFb = view.findViewById(R.id.fb);
        mTextMessageFb.setText("Partage sur Facebook");



        return view;
    }
}

