package com.example.joelg.lion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.joelg.lion.db.DaoSession;
import com.example.joelg.clapp.R;

import java.util.List;

public class JobActivity extends AppCompatActivity {

    public TextView TsView;
    private View.OnClickListener checkBoxListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        //    Toolbar HeaderToolbar = (Toolbar) findViewById(R.id.Task_header);
        //  setSupportActionBar(HeaderToolbar);
        RecyclerView RV = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        RV.setLayoutManager(LLM);
        RV.setHasFixedSize(true);
        TsView = (TextView) findViewById(R.id.TaskTS);


        final DaoSession daoSession = ((Lion) getApplication()).getDaoSession();

        // Put this in a different thread or use AsyncSession in greenDAO.
        // For Demo purpose, this query is made on main thread but it should  a different thread.
        User user = daoSession.getUserDao().load(1L);
        final List<Task> jobList = ((Lion) getApplication()).getDaoSession().getTaskDao().loadAll();
        Log.d("DEBUG_RV", "Loaded itms : " + jobList.size());
        TaskAdapter TaskAdapter = new TaskAdapter(jobList);
        RV.setAdapter(TaskAdapter);


    }


}








