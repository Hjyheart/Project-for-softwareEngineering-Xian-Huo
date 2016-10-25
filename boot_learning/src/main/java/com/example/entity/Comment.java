package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deado on 2016/10/24.
 */
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue
    @Column(nullable = false, name = "ID")
    private long ID;
    @Column(nullable = false, name = "STUDENTID")
    private String STUDENTID;
    @Column(nullable = false, name = "TARGETID")
    private String TARGETID;
    @Column(nullable = false, name = "TARGETTYPE")
    private Integer TARGETTYPE; // 0 -- club, 1 -- activity
    @Column(nullable = false, name = "CONTENT")
    private String  CONTENT;
    @Column(nullable = false, name = "DATE")
    private Date   DATE;

    public Comment(String STUDENTID, String TARGETID, Integer TARGETTYPE, String CONTENT, Date DATE) {
        this.STUDENTID = STUDENTID;
        this.TARGETID = TARGETID;
        this.TARGETTYPE = TARGETTYPE;
        this.CONTENT = CONTENT;
        this.DATE = DATE;
    }

    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Club club;

    // data get & set ways


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSTUDENTID() {
        return STUDENTID;
    }

    public void setSTUDENTID(String STUDENTID) {
        this.STUDENTID = STUDENTID;
    }

    public String getTARGETID() {
        return TARGETID;
    }

    public void setTARGETID(String TARGETID) {
        this.TARGETID = TARGETID;
    }

    public Integer getTARGETTYPE() {
        return TARGETTYPE;
    }

    public void setTARGETTYPE(Integer TARGETTYPE) {
        this.TARGETTYPE = TARGETTYPE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
}
