package com.example.joelg.lion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


// menu setup

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_main);
    }

    public void NewJob(View view) {
        Intent StartNewJob = new Intent(this, JobManagerActivity.class);
        startActivity(StartNewJob);

    }

    public void ExitApp(View view) {
        finish();
        moveTaskToBack(true);
    }

    public void Options(View view) {
        Intent StartOptions = new Intent(this, OptionsActivity.class);
        startActivity(StartOptions);


    }
}



