package com.example.entity;

import javax.persistence.*;

/**
 * Created by deado on 2016/10/22.
 */
@Entity @Table(name = "FILE")
public class File {
    @Id @Column(nullable = false, name = "ID")
    private String ID;//??
    @Column(nullable = false, name = "NAME")
    private String NAME;
    @Column(nullable = false, name = "URL")
    private String URL;
    @Column(nullable = false, name = "CLUB")
    private String CLUB;


    public File(String ID, String NAME, String URL, String CLUB) {
        this.ID = ID;
        this.NAME = NAME;
        this.URL = URL;
        this.CLUB = CLUB;
    }

    // data get & set way

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCLUB() {
        return CLUB;
    }

    public void setCLUB(String CLUB) {
        this.CLUB = CLUB;
    }
}
