package com.example.joelg.lion;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.joelg.clapp.R;
import com.example.joelg.lion.db.DaoSession;

import java.text.SimpleDateFormat;
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

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> TaskList;



    public TaskAdapter(List<Task> taskList) {
        TaskList = taskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("DEBUG_RV", "Attaching items to RV");
        Task task = TaskList.get(position);
        holder.tasktitle.setText(task.getTitle() + "");
        holder.IsDone.setText(task.getIsDone() + "");
        holder.Tstamp.setText(task.getTimeStamp() + "");
        holder.id = task.getId();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private Long id;
        private TextView tasktitle;
        private CheckBox IsDone;
        private TextView Tstamp;
        private CardView cv;
        private Calendar calander;
        private SimpleDateFormat simpledateformat;
        private String date;
        private String ts;

        public MyViewHolder(View view) {
            super(view);
            cv = itemView.findViewById(R.id.cv);
            tasktitle = view.findViewById(R.id.task_title);
            IsDone = view.findViewById(R.id.isDone);
            Tstamp = view.findViewById(R.id.TaskTS);
            IsDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                    IsDone.setClickable(false);
                    //time stamp
                    calander = Calendar.getInstance();
                    simpledateformat = new SimpleDateFormat("HH:mm:ss");
                    date = simpledateformat.format(calander.getTime());
                    DaoSession daoSession = ((Lion) IsDone.getContext().getApplicationContext()).getDaoSession();
                    com.example.joelg.lion.TaskDao taskDao = daoSession.getTaskDao();
                    Task t = taskDao.load(id);
                    t.setTimeStamp(date);
                    t.setIsDone(1);
                    Tstamp.setText(date);


                    //



                }
            });


        }

    }


}