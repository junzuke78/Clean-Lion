package com.example.joelg.lion.Gallery;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.R;
import com.example.joelg.lion.db.DaoSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    public ArrayList<String> GalleryImage;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imgView;
        long id;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ImageView GalleryImg = findViewById(R.id.GalleryImg);
        RecyclerView RVG = findViewById(R.id.g_rv);
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        GridLayout GGG = new GridLayout(this);

        RVG.setLayoutManager(LLM);
        RVG.setHasFixedSize(true);

        GGG.getRowCount();


        imgView = new ImageView(this);
        DaoSession daoSession = (( Lion ) imgView.getContext().getApplicationContext()).getDaoSession();
        String tempImg = daoSession.toString();
        Uri imgUri = Uri.parse(tempImg);
        Picasso.get().load(imgUri).centerCrop().into(GalleryImg);
        Log.d("DEBUG_LION", "Job manager activated");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.job_manager_menu, menu);
        Log.d("DEBUG_LION", "Image menu activated");
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

