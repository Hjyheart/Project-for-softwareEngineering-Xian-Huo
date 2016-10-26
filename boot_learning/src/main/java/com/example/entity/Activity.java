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
    private String ID;

    @Column(nullable = false, name = "NAME")
    private String NAME;

    @Column(nullable = false, name = "LOCATION")
    private String LOCATION;

    @Column(nullable = false, name = "TIME")
    private Date   TIME;

    @Column(nullable = false, name = "CONTACT")
    private String CONTACT;

    @Column(nullable = false, name = "PRAISE")
    private Integer PRAISE;

    @ManyToMany
    private Set<Student> students;

    @ManyToMany
    private Set<Club> clubs;

    @OneToMany
    private Set<Comment> comments;

    public Activity(String ID, String NAME, String LOCATION, Date TIME, String CONTACT, Integer PRAISE) {
        this.ID = ID;
        this.NAME = NAME;
        this.LOCATION = LOCATION;
        this.TIME = TIME;
        this.CONTACT = CONTACT;
        this.PRAISE = PRAISE;
    }

    //data get & set ways


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

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public Integer getPRAISE() {
        return PRAISE;
    }

    public void setPRAISE(Integer PRAISE) {
        this.PRAISE = PRAISE;
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
