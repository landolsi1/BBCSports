package com.appsolute.rami.bbcsports;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsolute.rami.bbcsports.Controller.SportAdapter;
import com.appsolute.rami.bbcsports.DAO.SportDAO;
import com.appsolute.rami.bbcsports.Entities.Sport;

import java.util.ArrayList;

/**
 * Created by Rami on 1/5/2018.
 */

public class FavoriteFragment extends Fragment {
    private ListView myList;
    public ArrayList<Sport> sportsList;
    SportAdapter adapter;
    String TAG = MenuActivity.class.getSimpleName();

    public static FavoriteFragment newInstance() {

        return new FavoriteFragment();
    }

    



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView mTextMessage;
        sportsList = new ArrayList<>();
        final View myFrag= inflater.inflate(R.layout.fragment_home, container, false);
        SportDAO dao = new SportDAO(getActivity());

        myList = myFrag.findViewById(R.id.listSport);
        //new FavoriteFragment().GetSports().execute();
        sportsList= dao.getAllSport();
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(    "bonjour"+i);
                Intent ii = new Intent(getActivity(),DetailFavorisActivity.class);
                Sport sp = sportsList.get(i);
                ii.putExtra("MyObjj", sp);

                System.out.println("le titre est : "+sportsList.get(i).getTitle());

                startActivity(ii);
            }
        });



        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SportDAO dao = new SportDAO(getActivity());
                removeItemFromList(i);
               // dao.deleteAll();



                return true;
            }
        });

        adapter = new SportAdapter(getActivity(), sportsList);
        myList.setAdapter(adapter);

        return myFrag;
    }


    protected void removeItemFromList(int position) {
        final int deletePosition = position;
        final SportDAO dao = new SportDAO(getActivity());
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setTitle("Suppression");
        alert.setMessage("Voulez vous supprimer cet élément?");
        alert.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deleteAll();
                sportsList.remove(deletePosition);
                for (int j=0; j<sportsList.size(); j++){
                    dao.insertSport(sportsList.get(j));
                }
                Toast.makeText(getActivity(), "Supprimé avec Succés ", Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();
            }
        });
        alert.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        alert.show();

    }
}
