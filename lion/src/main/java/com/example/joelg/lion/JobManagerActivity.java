package com.example.joelg.lion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.joelg.clapp.R;

import java.util.ArrayList;

//class name in java always starts with a UpperCase letter,
// always end the activity classes with the term Activity
public class JobManagerActivity extends AppCompatActivity {

    public ArrayList<String> jobmgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_manager);
        RecyclerView RVJM = (RecyclerView) findViewById(R.id.jm_rv);
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        RVJM.setLayoutManager(LLM);
        RVJM.setHasFixedSize(true);

        jobmgr = new ArrayList<>();
        jobmgr.add("first  Job");
        jobmgr.add("second Job");
        jobmgr.add("third  Job");
        jobmgr.add("fourth Job");
        jobmgr.add("fifth  Job");
        jobmgr.add("sixth  Job");


        JmAdapter jmAdapter = new JmAdapter(jobmgr);
        RVJM.setAdapter(jmAdapter);

        Log.d("DEBUG_CLAPP", "Job manager activated");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.job_manager_menu, menu);
        Log.d("DEBUG_CLAPP", "Job manager menu activated");

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("DEBUG_CLAPP", "Item selected :" + item.getItemId());
        switch (item.getItemId()) {

            //Job_mgr_list.add("Job");
            //if you want to start an activity

            default:
                return super.onOptionsItemSelected(item);
        }
        //private void OpenJob (View view)
    }


}
