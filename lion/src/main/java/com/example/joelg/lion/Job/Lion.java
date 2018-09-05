package com.example.joelg.lion.Job;

import android.app.Application;
import android.os.HandlerThread;
import android.text.format.Time;
import android.widget.Toast;

import com.example.joelg.lion.Task;
import com.example.joelg.lion.db.User;
import com.example.joelg.lion.db.DaoMaster;
import com.example.joelg.lion.db.DaoSession;

import java.io.IOException;


/**

 * Created by joelg on 20/11/2017.
 */

//singleton
public class Lion extends Application {

    private DaoSession mDaoSession;
    private HandlerThread DbHandler;

    @Override
    public void onCreate() {






        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "JobManagerList.db").getWritableDb()).newSession();
        //          | | |
        //JOEL THIS \/\/\/ NEEDS TO BE MOVED PLEASE FIX DaoGlobal TO ITS OWN FILE FOR SIMPLICITY ?!?!?!?
        new DaoMaster.DevOpenHelper(this, "JobList.db").getWritableDb();
        new DaoMaster.DevOpenHelper(this, "ImgList.db").getWritableDb();



        // USER CREATION
        if (mDaoSession.getUserDao().loadAll().size() == 0) {
            mDaoSession.getUserDao().insert(new User(1L, "Test", "", ""));

            //JOB CREATION FOR DEMO PURPOSE
        }

        /// Image DB Creation
        if (mDaoSession.getImgStoreDao().loadAll().size() == 0) {
            mDaoSession.getImgStoreDao().insert(new ImgStore("", "", "", 1L));
        }


        if (mDaoSession.getTaskDao().loadAll().size() == 0) {
            mDaoSession.getTaskDao().insert(new Task("Empty bin and replace liner thats nice", 0, "", 1L));
            //   (String title, String IsDone, String TimeStamp, Long id)
            mDaoSession.getTaskDao().insert(new Task("make beds", 0, "", 2L));
            mDaoSession.getTaskDao().insert(new Task("make couch", 0, "", 3L));
            mDaoSession.getTaskDao().insert(new Task("clean ligths", 0, "", 4L));
        }
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }





       public void OnDestroyed(){
        DbHandler.quitSafely();
        Toast.makeText(this,"DbThread Distroyed",Toast.LENGTH_LONG).show();

    }


}










        







