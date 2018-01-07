package com.appsolute.rami.bbcsports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.appsolute.rami.bbcsports.DAO.SportDAO;
import com.appsolute.rami.bbcsports.Entities.Sport;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TextView txtDescription,txtTitle,txtSource,txtDate;
    ImageView img,imgShare,imgAddFav;
    Toolbar toolbar;
    Sport sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String s= (String) getSupportActionBar().getTitle();


        Intent intent = getIntent();
        sport=(Sport) intent.getSerializableExtra("MyObj");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Détail: "+sport.getTitle().substring(0,10)+"...");

        txtDescription = findViewById(R.id.txtDetailDesc);
        txtTitle = findViewById(R.id.txtDetailTitle);
        txtSource = findViewById(R.id.txtDetailsource);
        txtDate = findViewById(R.id.txtDetailDate);
        img= findViewById(R.id.imgDetail);
       // imgShare= (ImageView)findViewById(R.id.imgShare);
        imgAddFav= findViewById(R.id.imgaddFav);

        txtDescription.setText(sport.getDescription());
        txtTitle.setText(sport.getTitle());
        txtDate.setText(sport.getPublishedAt());
        txtSource.setText(sport.getSource());

        loadImageFromUrl(sport.getUrlToImage());

        //Share On FaceBook
        final ShareButton fbShareButton = findViewById(R.id.fb_share_button);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(sport.getUrl()))
                .build();
        fbShareButton.setShareContent(content);

        // Finding the custom facebook share button
        TextView shareFB = findViewById(R.id.fb_share_button_custom);

        // Set a click listener on the custom facebook share button
        shareFB.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Share  on Facebook custom button is clicked on
            @Override
            public void onClick(View view) {
                fbShareButton.performClick();

            }
        });




        imgAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Ajouter");
                SportDAO dao = new SportDAO(getApplicationContext());
                if(compareSport(sport)==false){
                    long idL =  dao.insertSport(sport);
                Toast.makeText(getApplication(),"Ajouter au favoris...",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vous aimer déja", Toast.LENGTH_LONG).show();
                }
                System.out.println("le sport en question est: "+sport.getAuthor());
            }
        });
            System.out.println("vue a partir de Detail"+sport.getSource());
    }



    public boolean compareSport(Sport sprt){
        SportDAO sportDAO = new SportDAO(getApplicationContext());
        ArrayList<Sport> listes= new ArrayList<>();
        listes=sportDAO.getAllSport();
        Boolean b =false;
        for(int i = 0; i < listes.size(); i++) {
            Sport s = new Sport();
            s = listes.get(i);
            if (s.getTitle().equals(sprt.getTitle())) {
                System.out.println("existeeeeee!");
                b = true;
            }
        }
        return b;
    }



    private void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

}
