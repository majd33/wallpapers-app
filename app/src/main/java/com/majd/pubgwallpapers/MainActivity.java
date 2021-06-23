package com.majd.pubgwallpapers;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    /*Button bw ,br;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;*/
    private static int splash_time=700;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView iv=findViewById(R.id.welcom);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              Intent splash=new Intent(MainActivity.this,MainActivity2.class);
              startActivity(splash);
               finish();

            }
        },splash_time);

       /* bw=findViewById(R.id.pw);
        br=findViewById(R.id.rw);
        bw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent);
            }
        });

        dl=findViewById(R.id.activity_main);
        t= new ActionBarDrawerToggle(MainActivity.this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.all:
                        Intent intent1= new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent1);
                        break;
                    case R.id.random:
                        Intent intent2= new Intent(getApplicationContext(), MainActivity4.class);
                        startActivity(intent2);
                        break;
                    case R.id.share:
                        break;
                    case R.id.more:
                        break;
                    case R.id.pp:
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });*/
    }
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }*/
}