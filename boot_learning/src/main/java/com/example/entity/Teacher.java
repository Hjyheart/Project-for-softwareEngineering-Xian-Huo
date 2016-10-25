package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "TEACHER")
public class Teacher {

    @Id @Column(nullable = false, name = "ID")
    private String ID;
    @Column(nullable = false, name = "NAME")
    private String NAME;
    @Column( name = "CONTACT")
    private String CONTACT;

    public Teacher(String ID, String NAME, String CONTACT) {
        this.ID = ID;
        this.NAME = NAME;
        this.CONTACT = CONTACT;
    }

    // data get & set ways


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }
}
