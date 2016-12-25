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

    @Column(nullable = false, name = "TIME")
    private Date mDate;

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

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getmDate() {
        return mDate;
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
}
