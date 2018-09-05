package com.example.joelg.lion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.JobManager.JobManagerActivity;
import com.example.joelg.lion.db.DaoSession;

import org.greenrobot.greendao.annotation.NotNull;

public class MainActivity extends AppCompatActivity {


// menu setup
private static final int REQUEST_CAMERA_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_CAMERA_PERMISSION);

        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResullts) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResullts[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "you cant use camera without permission", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
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



