package com.example.joelg.lion.Gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.joelg.lion.R;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    public ArrayList<String> GalleryImage;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        RecyclerView RVG = findViewById(R.id.g_rv);
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        RVG.setLayoutManager(LLM);
        RVG.setHasFixedSize(true);
        GalleryImage = new ArrayList<>();

        GalleryAdapter galleryAdapter = new GalleryAdapter(GalleryImage);
        RVG.setAdapter(galleryAdapter);

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

            //Job_mgr_list.add("Job");
            //if you want to start an activity

            default:
                return super.onOptionsItemSelected(item);
        }
        //private void OpenJob (View view)
    }

}

