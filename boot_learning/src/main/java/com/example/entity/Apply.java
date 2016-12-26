package com.example.entity;


import javax.persistence.*;
import java.util.Date;

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

    @Column(nullable = false, name = "CLUBID")
    private Long mClubId;

    @Column(nullable = false, name = "TYPE")
    private Integer mType;

    @Column(nullable = false, name = "STIME")
    private Date mStartDate;

    @Column(nullable = false, name = "ETIME")
    private Date mEndDate;

    @Column(nullable = false, name = "LOCATION")
    private String mLocation;

    @Column(nullable = false, name = "DESCRIPTION")
    private String mDescription;

    @Column(nullable = false, name = "ACCEPT")
    private Integer mAccept;


    public Long getmId() {
        return mId;
    }

    public void setClubId(Long clubId) {
        this.mClubId = clubId;
    }

    public Long getClubId() {
        return mClubId;
    }

    public void setmClubId(Long mClubId) {
        this.mClubId = mClubId;
    }

    public Long getmClubId() {
        return mClubId;
    }

    public void setmEndDate(Date mEndDate) {
        this.mEndDate = mEndDate;
    }

    public Date getmEndDate() {
        return mEndDate;
    }

    public void setmStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
    }

    public Date getmStartDate() {
        return mStartDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Integer getmAccept() {
        return mAccept;
    }

    public void setmAccept(Integer mAccept) {
        this.mAccept = mAccept;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }
}
