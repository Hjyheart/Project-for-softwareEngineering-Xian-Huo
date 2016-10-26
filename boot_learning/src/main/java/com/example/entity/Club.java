package com.example.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "CLUB")
public class Club {
    @Id @GeneratedValue @Column(nullable = false, name = "ID")
    private long ID;

    @Column(nullable = false, name = "NAME")
    private String NAME;



    @Column(nullable = true, name = "TEACHER")
    private String TEACHER;


    @Column(nullable = true, name = "CHAIRMAN")
    private String CHAIRMANID; //

    @Column(nullable = true, name = "MEMBER_NUMBER")
    private Integer MEMBER_NUMBER;

    @ManyToMany
    private Set<Student> students;

    @ManyToMany
    private Set<Activity> activities;

    @OneToMany
    private Set<File> files;

    @OneToMany
    private Set<Comment> comments;

    //data get & set ways

    public Club(String NAME, String TEACHER, String CHAIRMAN, Integer MEMBER_NUMBER) {
        this.NAME = NAME;
        this.TEACHER = TEACHER;
        this.CHAIRMANID = CHAIRMAN;
        this.MEMBER_NUMBER = MEMBER_NUMBER;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTEACHER() {
        return TEACHER;
    }

    public void setTEACHER(String TEACHER) {
        this.TEACHER = TEACHER;
    }

    public Integer getMEMBER_NUMBER() {
        return MEMBER_NUMBER;
    }

    public void setMEMBER_NUMBER(Integer MEMBER_NUMBER) {
        this.MEMBER_NUMBER = MEMBER_NUMBER;
    }

    public String getCHAIRMANID() {
        return CHAIRMANID;
    }

    public void setCHAIRMANID(String CHAIRMANID) {
        this.CHAIRMANID = CHAIRMANID;
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

    public Set<File> getFiles() {
        return files;
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
