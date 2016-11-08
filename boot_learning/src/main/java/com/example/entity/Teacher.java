package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "TEACHER")
public class Teacher {

    @Id @Column(nullable = false, name = "ID")
    private String mId;
    @Column(nullable = false, name = "NAME")
    private String mName;
    @Column( name = "CONTACT")
    private String mContact;

    @OneToMany
    private Set<Apply> applies;

    public Teacher() {
    }

    public Teacher(String mId, String mName, String mContact) {

        this.mId = mId;
        this.mName = mName;
        this.mContact = mContact;
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

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    public Set<Apply> getApplies() {
        return applies;
    }

    public void setApplies(Set<Apply> applies) {
        this.applies = applies;
    }
}
