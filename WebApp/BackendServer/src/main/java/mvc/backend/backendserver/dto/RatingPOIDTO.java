package mvc.backend.backendserver.dto;

import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.entity.MyPOI;

import javax.persistence.*;
import java.sql.Date;

public class RatingPOIDTO {

    //private int ratingId;

    private int rate;

    private String comment;

    private Date timeCreate;

    private String title;
    private int accountId;
    private int poiID;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPoiID() {
        return poiID;
    }

    public void setPoiID(int poiID) {
        this.poiID = poiID;
    }
//    private Account account;
//
//    private MyPOI poi;
//
//    public int getRatingId() {
//        return ratingId;
//    }
//
//    public void setRatingId(int ratingId) {
//        this.ratingId = ratingId;
//    }
//
//    public int getRate() {
//        return rate;
//    }
//
//    public void setRate(int rate) {
//        this.rate = rate;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public Date getTimeCreate() {
//        return timeCreate;
//    }
//
//    public void setTimeCreate(Date timeCreate) {
//        this.timeCreate = timeCreate;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
//
//    public MyPOI getPoi() {
//        return poi;
//    }
//
//    public void setPoi(MyPOI poi) {
//        this.poi = poi;
//    }
}
