package com.example.joelg.lion.Gallery;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joelg.lion.Job.ImgStore;
import com.example.joelg.lion.Job.ImgStoreDao;
import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.R;
import com.example.joelg.lion.Task;
import com.example.joelg.lion.db.DaoSession;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {


    private List<ImgStore> imageList;
    private Uri imgUri;
    private Context context;

    public GalleryAdapter(Context context, List<ImgStore> imgList) {


        this.context = context;
        this.imageList = imgList;
    }


    public class GalleryHolder extends RecyclerView.ViewHolder {
        private Long id;
        private CardView cv;
        private ImageView imageView;

        public GalleryHolder(View view) {

            super(view);

            imageView = itemView.findViewById(R.id.galleryImg);

            //imgUri = Uri.parse(tempImg);
        }

    }

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_card_layout, parent, false);
        GalleryHolder holder = new GalleryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {


        ImgStore image = imageList.get(position);

        if (image.getImgURL().length() > 0)
            Picasso.get().load(imageList.get(position).getImgURL()).into(holder.imageView);
//        Picasso.get().load(image.getImgURL()).centerCrop().into(holder.imageView);

        Log.d("DEBUG_RV : ", image.getImgURL() + " : Attaching Image to RV");

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


}


