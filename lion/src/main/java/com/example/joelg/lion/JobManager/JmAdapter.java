package com.example.joelg.lion.JobManager;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joelg.lion.Job.JobActivity;
import com.example.joelg.lion.R;

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

public class JmAdapter extends RecyclerView.Adapter<JmAdapter.JmViewHolder> {

    private List<String> JobsList;
    private OnItemClickListener mListener;

    public JmAdapter(List<String> jobsList) {
        this.JobsList = jobsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public JmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jm_card_layout, parent, false);

        return new JmViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(JmViewHolder holder, int position) {

        Log.d("DEBUG_RV", "Attaching items to RV");
        holder.Jobtitle.setText(JobsList.get(position));


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return JobsList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);


    }

    public class JmViewHolder extends RecyclerView.ViewHolder {

        private Long id;
        private TextView Jobtitle;
        private CardView cv;

        public JmViewHolder(View view) {
            super(view);

            cv = itemView.findViewById(R.id.jm_cv);
            Jobtitle = view.findViewById(R.id.job_title);
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