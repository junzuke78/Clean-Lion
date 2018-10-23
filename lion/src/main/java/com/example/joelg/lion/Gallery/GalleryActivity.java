package com.example.joelg.lion.Gallery;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.joelg.lion.Job.ImgStore;
import com.example.joelg.lion.Job.ImgStoreDao;
import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.R;
import com.example.joelg.lion.Task;
import com.example.joelg.lion.db.DaoSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    public ArrayList<ImgStore> GalleryImageList;
    public ImageView galleryImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imgView;
        long id;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        DaoSession daoSession = (( Lion ) getApplication()).getDaoSession();
        List<ImgStore> images = daoSession.getImgStoreDao().loadAll();


        Log.d("DEBUG_RV", "Loaded images: " + images.size());

        RecyclerView RVG = findViewById(R.id.g_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(GalleryActivity.this, 2);
        RVG.setHasFixedSize(true);
        RVG.setLayoutManager(gridLayoutManager);

        GalleryAdapter galleryAdapter = new GalleryAdapter(getApplicationContext(), images);

        RVG.setAdapter(galleryAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.job_manager_menu, menu);
        Log.d("DEBUG_LION", "Gallery activated");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("DEBUG_LION", "Item selected :" + item.getItemId());
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}

