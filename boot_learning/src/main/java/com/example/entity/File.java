package com.example.entity;

import javax.persistence.*;

/**
 * Created by deado on 2016/10/22.
 */
@Entity @Table(name = "FILE")
public class File {
    @Id @Column(nullable = false, name = "ID")
    private String mId;//??
    @Column(nullable = false, name = "NAME")
    private String mName;
    @Column(nullable = false, name = "URL")
    private String mUrl;
    @Column(nullable = false, name = "CLUB")
    private String mClub;


    @ManyToOne
    private Club club;


    public File() {
    }

    public File(String mId, String mName, String mUrl, String mClub) {
        this.mId = mId;
        this.mName = mName;
        this.mUrl = mUrl;
        this.mClub = mClub;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
