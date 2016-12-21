package com.example.entity;

import javax.persistence.*;

/**
 * Created by deado on 2016/10/22.
 */
@Entity @Table(name = "CLUBFILE")
public class ClubFile {
    @Id
    @GeneratedValue
    @Column(nullable = false, name = "ID")
    private Long mId;//??
    @Column(nullable = false, name = "NAME")
    private String mName;
    @Column(nullable = false, name = "URL")
    private String mUrl;
    @Column(nullable = false, name = "CLUB")
    private String mClub;



    public ClubFile() {
    }

    public ClubFile(String mName, String mUrl, String mClub) {
        this.mName = mName;
        this.mUrl = mUrl;
        this.mClub = mClub;
    }

    public Long getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmClub() {
        return mClub;
    }

    public void setmClub(String mClub) {
        this.mClub = mClub;
    }


}
