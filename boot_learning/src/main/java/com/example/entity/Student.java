package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@Entity @Table(name = "STUDENT")
public class Student{

    @Id @Column(nullable = false, name = "ID")
    private String mId;
    @Column(nullable = false, name = "NAME")
    private String mName;
    @Column(nullable = true, name = "GRADE")
    private String mGrade;
    @Column(nullable = true, name = "MAJOR")
    private String mMajor;
    @Column(nullable = true, name = "CONTACT")
<<<<<<< HEAD
    private String CONTACT;
=======
    private String mContact;
>>>>>>> ZhangYinjia

    @ManyToMany
    private Set<Club> clubs;

    @ManyToMany
    private Set<Activity> activities;

<<<<<<< HEAD
    public Student(String ID, String NAME, String GRADE, String MAJOR, String CONTACT) {
        this.ID = ID;
        this.NAME = NAME;
        this.GRADE = GRADE;
        this.MAJOR = MAJOR;
        this.CONTACT = CONTACT;
=======
    public Student(String mId, String mName, String mGrade, String mMajor, String mCcontact) {
        this.mId = mId;
        this.mName = mName;
        this.mGrade = mGrade;
        this.mMajor = mMajor;
        this.mContact = mCcontact;
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
>>>>>>> ZhangYinjia
    }

    public String getmGrade() {
        return mGrade;
    }

    public void setmGrade(String mGrade) {
        this.mGrade = mGrade;
    }

    public String getmMajor() {
        return mMajor;
    }

    public void setmMajor(String mMajor) {
        this.mMajor = mMajor;
    }

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    public Set<Club> getClubs() {
        return clubs;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
