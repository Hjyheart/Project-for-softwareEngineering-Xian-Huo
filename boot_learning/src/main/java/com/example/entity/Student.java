package com.example.entity;

import com.example.service.EncryptionService;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@Entity @Table(name = "STUDENT")
public class Student{

    @Id @Column(nullable = false, name = "ID")
    private String mId;
    @Column(nullable = false,name = "PASSWORD")
    private String mPassword;
    @Column(nullable = false, name = "NAME")
    private String mName;
    @Column(nullable = true, name = "GRADE")
    private String mGrade;
    @Column(nullable = true, name = "MAJOR")
    private String mMajor;
    @Column(nullable = true, name = "CONTACT")
    private String mContact;
    @Column(nullable = true, name = "HEADIMG")
    private String mHeadUrl;


    @ManyToMany
    private List<Club> clubs;

    @ManyToMany
    private List<Activity> activities;

    @ManyToMany
    private List<Activity> favouriteactivities;

    @OneToMany
    private List<Apply> sendApplies;

    @OneToMany
    private List<Apply> receiveApplies;



    public Student(){

    }

    public Student(String mId, String mPassword, String mName,
                   String mGrade, String mMajor, String mContact, String mHeadUrl) throws Exception {
        this.mId = mId;
        this.mHeadUrl = mHeadUrl;
        this.mName = mName;
        this.mGrade = mGrade;
        this.mMajor = mMajor;
        this.mContact = mContact;

        try{
            EncryptionService encryptionService = new EncryptionService();
            this.mPassword = encryptionService.encipher(mPassword) + encryptionService.encipher(this.mId);
        }catch(Exception e){
            throw e;
        }

    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) throws Exception{
        try{
            EncryptionService encryptionService = new EncryptionService();
            this.mPassword = encryptionService.encipher(mPassword) + encryptionService.encipher(this.mId);
        }catch(Exception e){
            throw e;
        }
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
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

    @JsonBackReference
    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    @JsonBackReference
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @JsonBackReference
    public List<Activity> getFavouriteactivities() {
        return favouriteactivities;
    }

    public void setFavouriteactivities(List<Activity> favouriteactivities) {
        this.favouriteactivities = favouriteactivities;
    }

    @JsonBackReference
    public List<Apply> getSendApplies() {
        return sendApplies;
    }

    public void setSendApplies(List<Apply> sendApplies) {
        this.sendApplies = sendApplies;
    }

    @JsonBackReference
    public List<Apply> getReceiveApplies() {
        return receiveApplies;
    }

    public void setReceiveApplies(List<Apply> receiveApplies) {
        this.receiveApplies = receiveApplies;
    }

    public String getmHeadUrl() {
        return mHeadUrl;
    }

    public void setmHeadUrl(String mHeadUrl) {
        this.mHeadUrl = mHeadUrl;
    }
}
