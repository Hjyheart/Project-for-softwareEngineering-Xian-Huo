package com.example.entity;


import javax.persistence.*;

/**
 * Created by deado on 2016/11/2.
 */
@Entity
@Table(name="APPLY")
public class Apply {
    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mId;

    @Column(nullable = false, name = "FROMAN")
    private String mFromId;

    @Column(nullable = false, name = "TOMAN")
    private String mToId;

    @Column(nullable = false, name = "TYPE")
    private Integer mType; // 0 -- poster, 1 -- classroom, 2 -- ground, 4 -- register, 5 -- message

    @Column(nullable = false, name = "DESCRIPTION")
    private String mDescription;

    @Column(nullable = false, name = "ACCEPT")
    private Boolean mAccept;


    //public


    public Apply() {
    }

    public Apply(String mFromId, String mToId, Integer mType, String mDescription) {
        this.mFromId = mFromId;
        this.mToId = mToId;
        this.mType = mType;
        this.mDescription = mDescription;
        this.mAccept = mAccept;
    }

    public Long getmId() {
        return mId;
    }

    public String getmFromId() {
        return mFromId;
    }

    public void setmFromId(String mFromId) {
        this.mFromId = mFromId;
    }

    public String getmToId() {
        return mToId;
    }

    public void setmToId(String mToId) {
        this.mToId = mToId;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Boolean getmAccept() {
        return mAccept;
    }

    public void setmAccept(Boolean mAccept) {
        this.mAccept = mAccept;
    }
}
