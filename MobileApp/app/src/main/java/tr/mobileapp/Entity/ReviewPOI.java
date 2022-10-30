package tr.mobileapp.Entity;

import java.sql.Date;
import java.util.ArrayList;

public class ReviewPOI {

    private int ratingId;
    private int rate;
    private String comment;
    private Date timeCreate;
    private String accountName;
    private int poiId;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoiId() {
        return poiId;
    }

    public void setPoiId(int poiId) {
        this.poiId = poiId;
    }

    private ArrayList<Account> getAccountCommentReviewPOI = new ArrayList<>();

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public ReviewPOI() {
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

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
    public ArrayList<Account> GetAccountCommentReviewPOI() {return getAccountCommentReviewPOI;}
    public void SetAccountCommentReviewPOI(ArrayList<Account> getAccountCommentReviewPOI ) {this.getAccountCommentReviewPOI =getAccountCommentReviewPOI;}


}
