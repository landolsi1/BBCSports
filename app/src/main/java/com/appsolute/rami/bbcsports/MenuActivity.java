package com.appsolute.rami.bbcsports;



import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("hello from case");
                    getSupportActionBar().setTitle(R.string.title_home);

                    loadHomeFragment();
                   return true;
                case R.id.navigation_favoris:
                   loadFavorisFragment();
                   getSupportActionBar().setTitle(R.string.title_favoirs);
                  return true;
                case R.id.navigation_more:
                    getSupportActionBar().setTitle(R.string.title_more);
                //    mTextMessage =(TextView)findViewById(R.id.fragment_infos);
                //    mTextMessage.setText("Bonjour every Body");
                   loadInfosFragment();
                    return true;
            }
            return false;
        }
    };


    private void loadInfosFragment() {
        InfosFragment infosFragment = InfosFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, infosFragment);
        ft.commit();
    }

   private void loadHomeFragment() {

        System.out.println("hello from load");
        HomeFragment fragmentH = HomeFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragmentH);
        ft.commit();

    }



    private void loadFavorisFragment() {
        FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, favoriteFragment);
        ft.commit();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        System.out.println("hello from on create");


        if(isNetworkAvailable()){
           // Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();

        }else{
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{

                        destroy();
                    }
                }
            };
            timerThread.start();
            Toast.makeText(getApplicationContext(),"Pas de connexion INTERNET !",Toast.LENGTH_LONG).show();

        }

        if (savedInstanceState==null){
            loadHomeFragment();
        }

    }


    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
