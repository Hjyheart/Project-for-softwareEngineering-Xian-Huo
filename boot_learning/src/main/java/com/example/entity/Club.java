package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "CLUB")
public class Club {
    @Id @GeneratedValue @Column(nullable = false, name = "ID")
    private long mId;

    @Column(nullable = false, name = "NAME")
    private String mName;



    @Column(nullable = true, name = "TEACHER")
    private String mTeacher;


    @Column(nullable = true, name = "CHAIRMAN")
<<<<<<< HEAD
    private String CHAIRMANID; //
=======
    private String mChairmanId; //
>>>>>>> ZhangYinjia

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

    @ManyToMany
    private Set<Student> students;

    @ManyToMany
    private Set<Activity> activities;

    @OneToMany
    private Set<File> files;

    @OneToMany
    private Set<Comment> comments;

    //data get & set ways

<<<<<<< HEAD
    public Club(String NAME, String TEACHER, String CHAIRMAN, Integer MEMBER_NUMBER) {
        this.NAME = NAME;
        this.TEACHER = TEACHER;
        this.CHAIRMANID = CHAIRMAN;
        this.MEMBER_NUMBER = MEMBER_NUMBER;
=======

    public Club(String mName, String mTeacher, String mChairmanId, Integer mMemberNumber) {
        this.mName = mName;
        this.mTeacher = mTeacher;
        this.mChairmanId = mChairmanId;
        this.mMemberNumber = mMemberNumber;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
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
>>>>>>> ZhangYinjia
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

<<<<<<< HEAD
    public String getCHAIRMAN() {
        return CHAIRMANID;
    }

    public void setCHAIRMAN(String CHAIRMAN) {
        this.CHAIRMANID = CHAIRMAN;
=======
    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
>>>>>>> ZhangYinjia
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
