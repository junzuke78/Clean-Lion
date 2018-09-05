package com.example.joelg.lion.Job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.joelg.lion.Camera.CameraActivity;
import com.example.joelg.lion.NotesActivity;
import com.example.joelg.lion.R;
import com.example.joelg.lion.Task;
import com.example.joelg.lion.db.User;
import com.example.joelg.lion.db.DaoSession;

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
        RecyclerView RV = findViewById(R.id.recycler_view);
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        RV.setLayoutManager(LLM);
        RV.setHasFixedSize(true);
        TsView = findViewById(R.id.TaskTS);


        final DaoSession daoSession = (( Lion ) getApplication()).getDaoSession();

        // Put this in a different thread or use AsyncSession in greenDAO.
        // For Demo purpose, this query is made on main thread but it should  a different thread.
        User user = daoSession.getUserDao().load(1L);
        final List<Task> jobList = (( Lion ) getApplication()).getDaoSession().getTaskDao().loadAll();
        Log.d("DEBUG_RV", "Loaded itms : " + jobList.size());
        JobAdapter JobAdapter = new JobAdapter(jobList);
        RV.setAdapter(JobAdapter);


        final ImageButton CameraButton = findViewById(R.id.CameraBtn);
        CameraButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                {
                    Intent intent = new Intent(JobActivity.this, CameraActivity.class);
                    startActivity(intent);

                }

            }
        });

        final ImageButton BackButton = findViewById(R.id.JobBackBtn);
        BackButton.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {
                {
                    finish();
                }

            }
        });

        final ImageButton NotesButton = findViewById(R.id.noteBtn);
        NotesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                {
                    Intent intent = new Intent(JobActivity.this, NotesActivity.class);
                    startActivity(intent);
                }

            }
        });


    }


}








