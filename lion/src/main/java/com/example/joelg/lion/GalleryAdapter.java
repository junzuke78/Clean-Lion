package com.example.joelg.lion;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_card_layout, parent, false);

        return new ImageViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        Log.d("DEBUG_RV", "Attaching items to RV");


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return ImageList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);


    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private Long id;
        private CardView cv;
        private ImageView imgView;

        public ImageViewHolder(View view) {
            super(view);

            cv = itemView.findViewById(R.id.jm_cv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent;
                    intent = new Intent(itemView.getContext(), JobActivity.class);
                    itemView.getContext().startActivity(intent);

                }
            });

        }

    }


}