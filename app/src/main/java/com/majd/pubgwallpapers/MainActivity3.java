package com.majd.pubgwallpapers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;

import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity3 extends AppCompatActivity {

    ImageView imageView, imageset, imagesave, imageshare;
    imageAdapter imageAdapter;
    String fileuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView= (ImageView) findViewById(R.id.image_view);
        imageset= (ImageView) findViewById(R.id.set);
        imagesave= (ImageView) findViewById(R.id.save);
        imageshare= (ImageView) findViewById(R.id.shareim);

        Intent i=getIntent();
        final int position=i.getExtras().getInt("id");
        imageAdapter=new imageAdapter(this);
        String url = imageAdapter.imageArray[position];
        Picasso.get().load(url).placeholder(R.drawable.load_pro).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(imageView);

        imageset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("Set As Wallpaper :");
                String[] item= {"Home screen" , "Lock screen", "Home and lock screens"};
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = imageAdapter.imageArray[position];
                        switch (which){
                            case 0:
                            {
                                Target target= new Target() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                                        try {
                                            wallpaperManager.setBitmap(bitmap, null, true,WallpaperManager.FLAG_SYSTEM);
                                            Toast.makeText(MainActivity3.this, "Set wallpaper is Successfully", Toast.LENGTH_SHORT).show();

                                        } catch (IOException e) {
                                            Toast.makeText(MainActivity3.this, "Set Wallpaper is failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {}
                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {}};
                                Picasso.get().load(url).into(target);
                            }
                            break;
                            case 1:
                            {
                                Target target= new Target() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                                        try {
                                            wallpaperManager.setBitmap(bitmap, null, true,WallpaperManager.FLAG_LOCK);
                                            Toast.makeText(MainActivity3.this, "Set wallpaper is Successfully", Toast.LENGTH_SHORT).show();

                                        } catch (IOException e) {
                                            Toast.makeText(MainActivity3.this, "Set Wallpaper is failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {}
                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {}};
                                Picasso.get().load(url).into(target);

                            }break;
                            case 2:
                            {
                                Target target= new Target() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                                        try {
                                            wallpaperManager.setBitmap(bitmap);
                                            Toast.makeText(MainActivity3.this, "Set wallpaper is Successfully", Toast.LENGTH_SHORT).show();

                                        } catch (IOException e) {
                                            Toast.makeText(MainActivity3.this, "Set Wallpaper is failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {}
                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {}};
                                Picasso.get().load(url).into(target);
                            }break;
                        }
                    }
                });
                builder.show();
            }
        });
        imagesave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MainActivity3.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(MainActivity3.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                String url = imageAdapter.imageArray[position];
                Uri uri=Uri.parse(url);

                if(havenetwork()){
                try {
                    DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                            .setAllowedOverRoaming(false)
                            .setTitle("pupg Wallpaper")
                            .setMimeType("image/jpeg")
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + "PupgWallpaper" + ".jpg");
                    downloadManager.enqueue(request);
                    Toast.makeText(MainActivity3.this, "Image download started", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    Toast.makeText(MainActivity3.this, "Image download failed", Toast.LENGTH_SHORT).show();
                }} else
                    Toast.makeText(MainActivity3.this, "Internet not found", Toast.LENGTH_SHORT).show();

            }
        });

        imageshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = imageAdapter.imageArray[position];

                Picasso.get().load(url).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        try {
                            File mydir =new File(Environment.getExternalStorageDirectory()+"/pubg");
                            if(!mydir.exists())
                                mydir.mkdirs();
                             fileuri =mydir.getAbsolutePath()+File.separator+System.currentTimeMillis()+".jpg";
                            FileOutputStream outputStream=new FileOutputStream(fileuri);
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                            outputStream.flush();
                            outputStream.close();
                        }catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        Uri uri =Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), BitmapFactory.decodeFile(fileuri),null,null));
                        Intent share =new Intent(Intent.ACTION_SEND);
                        share.setType("image/*");
                        share.putExtra(Intent.EXTRA_STREAM, uri);
                        startActivity(Intent.createChooser(share, "Share Image"));
                    }
                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {}
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {}
                });
            }
        });
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

}