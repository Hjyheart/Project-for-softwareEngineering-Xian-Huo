package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by deado on 2016/10/22.
 */
@Entity @Table(name = "ACTIVITY")
public class Activity {
    @Id
    @GeneratedValue
    @Column(nullable = false, name = "ID")
    private Long mId;

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

    @Column(nullable = true, name = "IMGURL")
    private String mImgUrl;

    @Column(nullable = true, name = "DESCRIPTION")
    private String mDescription;

    @ManyToMany
    private List<Student> students;

    @ManyToMany
    private List<Club> clubs;

    @OneToMany
    private List<Comment> comments;

    public Activity(){}


    public Activity(String mName, String mLocation, Date mTime, String mContact,
                    Integer mPraise, String mImgUrl, String mDescription) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mTime = mTime;
        this.mContact = mContact;
        this.mPraise = mPraise;
        this.mImgUrl = mImgUrl;
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

    public String getmImgUrl() {
        return mImgUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @JsonBackReference
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @JsonBackReference
    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
