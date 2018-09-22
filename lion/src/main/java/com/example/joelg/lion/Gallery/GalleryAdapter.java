package com.example.joelg.lion.Gallery;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.joelg.lion.Job.ImgStore;
import com.example.joelg.lion.R;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {

    private ArrayList<ImgStore> ImgList;


    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_card_layout, parent, false);

        return new GalleryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {

        Log.d("DEBUG_RV", "Attaching items to RV");


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return ImgList.size();
    }

    public class GalleryHolder extends RecyclerView.ViewHolder {

        private Long id;
        private CheckBox IsDone;
        private CardView cv;


        public GalleryHolder(View view) {
            super(view);


        }


    }
}


