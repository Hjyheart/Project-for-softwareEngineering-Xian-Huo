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
    private Long mId;
    @Column(nullable = false, name = "STUDENTID")
    private String mStudentId;
    @Column(nullable = false, name = "TARGETID")
    private Long mTargetId;
    @Column(nullable = false, name = "TARGETTYPE")
    private Integer mTargetType; // 0 -- club, 1 -- activity
    @Column(nullable = false, name = "CONTENT")
    private String  mContent;
    @Column(nullable = false, name = "DATE")
    private Date   mDate;

    private String studentName;


    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Club club;

    public Comment(){}



    public Comment(String mStudentId, Long mTargetId, Integer mTargetType, String mContent, Date mDate) {

        this.mStudentId = mStudentId;
        this.mTargetId = mTargetId;
        this.mTargetType = mTargetType;
        this.mContent = mContent;
        this.mDate = mDate;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmStudentId() {
        return mStudentId;
    }

    public void setmStudentId(String mStudentId) {
        this.mStudentId = mStudentId;
    }

    public Long getmTargetId() {
        return mTargetId;
    }

    public void setmTargetId(Long mTargetId) {
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

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }
}
