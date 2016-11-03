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
    @Column(nullable = false, name = "ID")
    private String mId;
    @Column(nullable = false, name = "STUDENTID")
    private String mStudentId;
    @Column(nullable = false, name = "TARGETID")
    private String mTargetId;
    @Column(nullable = false, name = "TARGETTYPE")
    private Integer mTargetType; // 0 -- club, 1 -- activity
    @Column(nullable = false, name = "CONTENT")
    private String  mContent;
    @Column(nullable = false, name = "DATE")
    private Date   mDate;


    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Club club;


    public Comment() {
    }

    public Comment(String mStudentId, String mTargetId, Integer mTargetType, String mContent, Date mDate) {

        this.mStudentId = mStudentId;
        this.mTargetId = mTargetId;
        this.mTargetType = mTargetType;
        this.mContent = mContent;
        this.mDate = mDate;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmStudentId() {
        return mStudentId;
    }

    public void setmStudentId(String mStudentId) {
        this.mStudentId = mStudentId;
    }

    public String getmTargetId() {
        return mTargetId;
    }

    public void setmTargetId(String mTargetId) {
        this.mTargetId = mTargetId;
    }

    public Integer getmTargetType() {
        return mTargetType;
    }

    public void setmTargetType(Integer mTargetType) {
        this.mTargetType = mTargetType;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
