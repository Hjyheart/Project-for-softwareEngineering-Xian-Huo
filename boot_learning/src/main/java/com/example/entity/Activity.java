package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Entity @Table(name = "ACTIVITY")
public class Activity {
    @Id @Column(nullable = false, name = "ID")
    private String mId;

    @Column(nullable = false, name = "NAME")
    private String mName;

    @Column(nullable = false, name = "LOCATION")
    private String mLocation;

    @Column(nullable = false, name = "TIME")
    private Date   mTime;

    @Column(nullable = false, name = "CONTACT")
    private String mContact;

    @Column(nullable = false, name = "PRAISE")
    private Integer mPraise;

    @ManyToMany
    private Set<Student> students;

    @ManyToMany
    private Set<Club> clubs;

    @OneToMany
    private Set<Comment> comments;


    public Activity() {
    }

    public Activity(String mId, String mName, String mLocation, Date mTime, String mContact, Integer mPraise) {
        this.mId = mId;
        this.mName = mName;
        this.mLocation = mLocation;
        this.mTime = mTime;
        this.mContact = mContact;
        this.mPraise = mPraise;
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

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    public Integer getmPraise() {
        return mPraise;
    }

    public void setmPraise(Integer mPraise) {
        this.mPraise = mPraise;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Club> getClubs() {
        return clubs;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
