package com.appsolute.rami.bbcsports;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsolute.rami.bbcsports.Entities.Sport;
import com.squareup.picasso.Picasso;

public class DetailFavorisActivity extends AppCompatActivity {
    TextView txtDescription,txtTitle,txtSource,txtDate;
    ImageView img;
    Sport sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favoris);

        Intent intent = getIntent();
        sport=(Sport) intent.getSerializableExtra("MyObjj");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtDescription = findViewById(R.id.txtDetailDescFav);
        txtTitle = findViewById(R.id.txtDetailTitleFav);
        txtSource = findViewById(R.id.txtDetailsourceFav);
        txtDate = findViewById(R.id.txtDetailDateFav);
        img= findViewById(R.id.imgDetailFav);
        //imgShare= (ImageView)findViewById(R.id.imgShareFav);

       // getSupportActionBar().setTitle(R.string.detail_favoris);
        getSupportActionBar().setTitle("Détail: "+sport.getTitle().substring(0,10)+"...");

        txtDescription.setText(sport.getDescription());
        txtTitle.setText(sport.getTitle());
        txtDate.setText(sport.getPublishedAt());
        txtSource.setText(sport.getSource());

        loadImageFromUrl(sport.getUrlToImage());



/*
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(sport.getUrl()))
                .build();

*/

       //         ShareButton shareButton = (ShareButton) findViewById(R.id.fb_share_button);
      //  shareButton.setShareContent(content);

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
