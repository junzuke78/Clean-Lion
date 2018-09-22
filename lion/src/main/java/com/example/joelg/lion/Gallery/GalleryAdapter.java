package com.example.joelg.lion.Gallery;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.joelg.lion.Job.ImgStore;
import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.R;
import com.example.joelg.lion.Task;
import com.example.joelg.lion.db.DaoSession;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter {
    public class JobAdapter extends RecyclerView.Adapter<JobAdapter.GalleryHolder> {

        private ArrayList<ImgStore> ImgList;


        public JobAdapter(List<ImgStore> imgList) {

        }

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
            private TextView Tstamp;
            private CardView cv;


            public GalleryHolder(View view) {
                super(view);
                cv = itemView.findViewById(R.id.gallery_cv);
                IsDone = view.findViewById(R.id.isDone);
                Tstamp = view.findViewById(R.id.TaskTS);
                IsDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        DaoSession daoSession = (( Lion ) IsDone.getContext().getApplicationContext()).getDaoSession();
                        com.example.joelg.lion.TaskDao taskDao = daoSession.getTaskDao();
                        Task t = taskDao.load(id);


                    }
                });


            }

        }


    }
}

