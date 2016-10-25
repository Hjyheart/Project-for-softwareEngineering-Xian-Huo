package com.example.entity;

import javax.persistence.*;

/**
 * Created by deado on 2016/10/22.
 */

@Entity @Table(name = "CLUB", schema = "test")
public class Club {
    @Id @GeneratedValue @Column(nullable = false, name = "ID")
    private long ID;

    @Column(nullable = false, name = "NAME")
    private String NAME;



    @Column(nullable = true, name = "TEACHER")
    private String TEACHER;


    @Column(nullable = true, name = "CHAIRMAN")
    private String CHAIRMAN;

    @Column(nullable = true, name = "MEMBER_NUMBER")
    private Integer MEMBER_NUMBER;

    //data get & set ways

    public Club(String NAME, String TEACHER, String CHAIRMAN, Integer MEMBER_NUMBER) {
        this.NAME = NAME;
        this.TEACHER = TEACHER;
        this.CHAIRMAN = CHAIRMAN;
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

    public String getCHAIRMAN() {
        return CHAIRMAN;
    }

    public void setCHAIRMAN(String CHAIRMAN) {
        this.CHAIRMAN = CHAIRMAN;
    }

    public Integer getMEMBER_NUMBER() {
        return MEMBER_NUMBER;
    }

    public void setMEMBER_NUMBER(Integer MEMBER_NUMBER) {
        this.MEMBER_NUMBER = MEMBER_NUMBER;
    }
}
