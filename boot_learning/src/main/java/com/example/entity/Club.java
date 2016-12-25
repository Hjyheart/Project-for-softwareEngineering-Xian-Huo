package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "CLUB")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID")
    private Long mId;

    @Column(nullable = false, name = "NAME")
    private String mName;

    @Column(nullable = false, name = "STATE")
    private Integer mState;

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

    @Column(nullable = true, name = "CONTENT", length = 5000)
    private String content;


    @ManyToMany
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClubFile> clubfiles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    //data get & set ways



    public Club(){}

    public Club(String mName, String mTeacher, String mChairmanId, Integer mMemberNumber, String mDescription, int state) {
        this.mName = mName;
        this.mTeacher = mTeacher;
        this.mChairmanId = mChairmanId;
        this.mMemberNumber = mMemberNumber;
        this.mDescription = mDescription;
        this.mState = state;
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

    @JsonBackReference
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @JsonBackReference
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setmState(Integer mState) {
        this.mState = mState;
    }

    public Integer getmState(){
        return mState;
    }

}
