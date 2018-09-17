package com.example.joelg.lion.Gallery;

import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.joelg.lion.Job.JobActivity;
import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.R;
import com.example.joelg.lion.db.DaoSession;

import java.util.List;


/**
 * Created by joelg on 5/12/2017.
 */

/**
 * IO -> blocking
 * ---
 * -----
 * ---
 * ---
 * --
 * ---
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageViewHolder> {

    private List<String> ImageList;
    private OnItemClickListener mListener;
    public GalleryAdapter(List<String> imageList) {
        this.ImageList = imageList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ImageView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_card_layout, parent, false);


        return new ImageViewHolder(ImageView);


    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        Log.d("DEBUG_RV", "Attaching items to RV");


    }



    @Override
    public int getItemCount() {
        return ImageList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);


    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private Long id;
        private CardView cv;
        private ImageView imgView;
        private int Time;




        public ImageViewHolder(View ImageView) {
            super(ImageView);

            DaoSession daoSession = (( Lion ) itemView.getContext().getApplicationContext()).getDaoSession();

            cv = imgView.findViewById(R.id.gallery_cv);



            }


                }


        }




