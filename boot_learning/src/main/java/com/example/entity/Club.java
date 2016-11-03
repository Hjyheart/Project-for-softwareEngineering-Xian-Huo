package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "CLUB")
public class Club {
    @Id @Column(nullable = false, name = "ID")
    private String mId;

    @Column(nullable = false, name = "NAME")
    private String mName;



    @Column(nullable = true, name = "TEACHER")
    private String mTeacher;


    @Column(nullable = true, name = "CHAIRMAN")
    private String mChairmanId;

    @Column(nullable = true, name = "MEMBER_NUMBER")
    private Integer mMemberNumber;


    @ManyToMany
    private Set<Student> students;

    @ManyToMany
    private Set<Activity> activities;

    @OneToMany
    private Set<File> files;

    @OneToMany
    private Set<Comment> comments;

    //data get & set ways


    public Club(){}



    public Club(String mName, String mTeacher, String mChairmanId, Integer mMemberNumber) {
        this.mName = mName;
        this.mTeacher = mTeacher;
        this.mChairmanId = mChairmanId;
        this.mMemberNumber = mMemberNumber;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }



    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
