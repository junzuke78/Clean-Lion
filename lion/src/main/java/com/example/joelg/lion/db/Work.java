package com.example.joelg.lion.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by joelg on 20/11/2017.
 */

@Entity(nameInDb = "Work")
public class Work {


    @Id(autoincrement = true)
    private Long id;

    //optional columns in the db
    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "status")
    private String status;

    @Generated(hash = 1246928701)
    public Work(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    @Generated(hash = 572069219)
    public Work() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
