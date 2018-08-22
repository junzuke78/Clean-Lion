package com.example.joelg.lion;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by joelg on 20/11/2017.
 */

@Entity(nameInDb = "ImgStore")
public class ImgStore {

    @Property(nameInDb = "IMGcount")
    private String IMGcount;


    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 1414930291)
    public ImgStore(String IMGcount, Long id) {
        this.IMGcount = IMGcount;
        this.id = id;
    }

    @Generated(hash = 1401329911)
    public ImgStore() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIMGcount() {
        return this.IMGcount;
    }

    public void setIMGcount(String IMGcount) {
        this.IMGcount = IMGcount;
    }
}

