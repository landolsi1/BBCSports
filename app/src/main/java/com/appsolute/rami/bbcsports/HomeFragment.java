package com.appsolute.rami.bbcsports;


import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appsolute.rami.bbcsports.Controller.SportAdapter;
import com.appsolute.rami.bbcsports.Entities.Sport;
import com.appsolute.rami.bbcsports.Utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rami on 1/4/2018.
 */

public class HomeFragment extends Fragment {

    private ListView myList;
    public ArrayList<Sport> sportsList;
    String TAG = MenuActivity.class.getSimpleName();

    private static String url = "https://newsapi.org/v2/top-headlines?sources=abc-news&apiKey=5b799ed4db354a909d4a5bd002c4e53e";




    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         TextView mTextMessage;

         sportsList = new ArrayList<>();


         final View myFrag= inflater.inflate(R.layout.fragment_home, container, false);

         myList = myFrag.findViewById(R.id.listSport);
         new GetSports().execute();

         myList.setOnItemClickListener(new AdapterView.OnItemClickListener()

         {


             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 System.out.println(    "bonjour"+i);
                 Intent ii = new Intent(getActivity(),DetailActivity.class);
                 Sport sp = sportsList.get(i);
                 ii.putExtra("MyObj", sp);

                 System.out.println("le titre est : "+sportsList.get(i).getTitle());

                 startActivity(ii);
             }
         });
        return myFrag;



    }



    private class GetSports extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            // Toast.makeText(getApplicationContext(),"Téléchargement des données ...",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Reponse de la source: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    JSONArray articles = jsonObj.getJSONArray("articles");


                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject c = articles.getJSONObject(i);

                        // Récupération des infos sources
                        JSONObject source = c.getJSONObject("source");
                        String id = source.getString("id");
                        String name = source.getString("name");

                        //récupération infos articles

                        String author = c.getString("author");
                        String title = c.getString("title");
                        String description = c.getString("description");

                        String url = c.getString("url");
                        String urlToImage = c.getString("urlToImage");
                        String publishedAt = c.getString("publishedAt");




                        // tmp hash map for single contact
                        HashMap<String, String> sports = new HashMap<>();

                        // adding each child node to HashMap key => value
                        Sport sport = new Sport();
                        sport.setAuthor(author);
                        sport.setPublishedAt(publishedAt);
                        sport.setSource(name);
                        sport.setTitle(title);
                        sport.setDescription(description);
                        sport.setUrl(url);
                     //   Bitmap b= BitmapFactory.decodeStream((InputStream) new URL(urlToImage).getContent());
                        sport.setUrlToImage(urlToImage);

                        // adding contact to contact list
                        sportsList.add(sport);

                    }
                } catch (final JSONException e) {


                }
            } else {


            }

            return null;
        }
        SportAdapter adapter;

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter = new SportAdapter(
                    getActivity(), sportsList);
            myList.setAdapter(adapter);



        }


    }


}
