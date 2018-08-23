package com.example.joelg.lion.Job;

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

    @Property(nameInDb = "ImgURL")
    private String ImgURL;

    @Property(nameInDb = "ImgTimeStamp")
    private String ImgTimeStamp;




    @Id(autoincrement = true)
    private Long id;


    @Generated(hash = 2069573104)
    public ImgStore(String IMGcount, String ImgURL, String ImgTimeStamp, Long id) {
        this.IMGcount = IMGcount;
        this.ImgURL = ImgURL;
        this.ImgTimeStamp = ImgTimeStamp;
        this.id = id;
    }


    @Generated(hash = 1401329911)
    public ImgStore() {
    }




    public String getIMGcount() {
        return this.IMGcount;
    }


    public void setIMGcount(String IMGcount) {
        this.IMGcount = IMGcount;
    }


    public String getImgURL() {
        return this.ImgURL;
    }


    public void setImgURL(String ImgURL) {
        this.ImgURL = ImgURL;
    }


    public String getImgTimeStamp() {
        return this.ImgTimeStamp;
    }


    public void setImgTimeStamp(String ImgTimeStamp) {
        this.ImgTimeStamp = ImgTimeStamp;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


}

