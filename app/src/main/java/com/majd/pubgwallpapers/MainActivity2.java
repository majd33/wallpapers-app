package com.majd.pubgwallpapers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.AdRequest;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {

    //private InterstitialAd mInterstitialAd;

    GridView gridView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

      /*  MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());*/

        if (havenetwork()) {
            gridView = findViewById(R.id.grid_view);
            gridView.setAdapter(new imageAdapter(this));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    /*if(mInterstitialAd.isLoaded())
                        mInterstitialAd.show();
                    else
                        Log.d("TAG","not loaded");*/

                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    intent.putExtra("id", position);
                    startActivity(intent);
                }
            });
        }else
        {
            AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity2.this);
            builder.setTitle("Pubg Wallpapers");
            builder.setMessage("Internet Not Found !!");
            /*builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });*/
            builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    restart();
                }
            });
            builder.show();
        }

        /*mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClicked() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });*/

        dl=findViewById(R.id.activity_main2);
        t= new ActionBarDrawerToggle(MainActivity2.this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv2);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.refresh:
                        Intent intent1= new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent1);
                        break;
                    case R.id.random:
                        //Intent intent2= new Intent(getApplicationContext(), MainActivity4.class);
                       // startActivity(intent2);
                        break;
                    case R.id.us:
                        Intent email=new Intent(Intent.ACTION_SEND);
                        email.setType("plain/text");
                        email.putExtra(Intent.EXTRA_EMAIL , new String[]{"majdalattari333@gmail.com"});
                        startActivity(Intent.createChooser(email,""));
                        break;
                    case R.id.share:
                        Intent shareIntent=new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT,"HD Pubg Wallpapers App : https:play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
                        shareIntent.setType("text/plain");
                        startActivity(shareIntent);
                        break;
                    case R.id.more:
                        Intent moreIntent=new Intent();
                        moreIntent.setAction(Intent.ACTION_SEND);
                        moreIntent.putExtra(Intent.EXTRA_TEXT,"HD Pubg Wallpapers App : https:play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
                        moreIntent.setType("text/plain");
                        startActivity(moreIntent);
                        break;
                    case R.id.pp:
                        Intent p= new Intent(getApplicationContext(), MainActivity5.class);
                        startActivity(p);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private boolean havenetwork()
    {
        boolean havewifi=false;
        boolean havemobile=false;

        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos= connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info:networkInfos)
        {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    havewifi= true;

            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    havemobile= true;
        }

        return havemobile||havewifi;

    }
    public void restart()
    {
        recreate();
    }
}