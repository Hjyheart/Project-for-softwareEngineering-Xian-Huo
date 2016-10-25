package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@Entity @Table(name = "STUDENT")
public class Student{

    @Id @Column(nullable = false, name = "ID")
    private String ID;
    @Column(nullable = false, name = "NAME")
    private String NAME;
    @Column(nullable = true, name = "GRADE")
    private String GRADE;
    @Column(nullable = true, name = "MAJOR")
    private String MAJOR;
    @Column(nullable = true, name = "CONTACT")
    private String CONTACT;

    @ManyToMany
    private Set<Club> clubs;

    @ManyToMany
    private Set<Activity> activities;

    public Student(String ID, String NAME, String GRADE, String MAJOR, String CONTACT) {
        this.ID = ID;
        this.NAME = NAME;
        this.GRADE = GRADE;
        this.MAJOR = MAJOR;
        this.CONTACT = CONTACT;
    }

    public String getGRADE() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE = GRADE;
    }

    public String getMAJOR() {
        return MAJOR;
    }

    public void setMAJOR(String MAJOR) {
        this.MAJOR = MAJOR;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
