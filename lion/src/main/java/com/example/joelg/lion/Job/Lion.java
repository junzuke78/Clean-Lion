package com.example.joelg.lion.Job;

import android.app.Application;

import com.example.joelg.lion.Task;
import com.example.joelg.lion.User;
import com.example.joelg.lion.db.DaoMaster;
import com.example.joelg.lion.db.DaoSession;


/**

 * Created by joelg on 20/11/2017.
 */

//singleton
public class Lion extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "CleanList.db").getWritableDb()).newSession();

        // USER CREATION
        if (mDaoSession.getUserDao().loadAll().size() == 0) {
            mDaoSession.getUserDao().insert(new User(1L, "", "", ""));
            //JOB CREATION FOR DEMO PURPOSE

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
}





        







