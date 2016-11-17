package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "CLUB")
public class Club {
    @Id
    @GeneratedValue
    @Column(nullable = false, name = "ID")
    private Long mId;

    @Column(nullable = false, name = "NAME")
    private String mName;



    @Column(nullable = true, name = "TEACHER")
    private String mTeacher;


    @Column(nullable = true, name = "CHAIRMAN")
    private String mChairmanId;

    @Column(nullable = true, name = "MEMBER_NUMBER")
    private Integer mMemberNumber;

    @Column(nullable = true, name = "DESCRIPTION")
    private String mDescription;

    @Column(nullable = true, name = "IMGURL")
    private String mImgUrl;


    @ManyToMany
    private List<Student> students;

    @ManyToMany
    private List<Activity> activities;

    @OneToMany
    private List<ClubFile> clubfiles;

    @OneToMany
    private List<Comment> comments;

    //data get & set ways



    public Club(){}

    public Club(String mName, String mTeacher, String mChairmanId, Integer mMemberNumber, String mDescription) {
        this.mName = mName;
        this.mTeacher = mTeacher;
        this.mChairmanId = mChairmanId;
        this.mMemberNumber = mMemberNumber;
        this.mDescription = mDescription;
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

    public String getmTeacher() {
        return mTeacher;
    }

    public void setmTeacher(String mTeacher) {
        this.mTeacher = mTeacher;
    }

    public String getmChairmanId() {
        return mChairmanId;
    }

    public void setmChairmanId(String mChairmanId) {
        this.mChairmanId = mChairmanId;
    }

    public Integer getmMemberNumber() {
        return mMemberNumber;
    }

    public void setmMemberNumber(Integer mMemberNumber) {
        this.mMemberNumber = mMemberNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<ClubFile> getClubfiles() {
        return clubfiles;
    }

    public void setClubfiles(List<ClubFile> clubfiles) {
        this.clubfiles = clubfiles;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }
}
