package tr.mobileapp.Entity;

public class ReviewPOI {
    double rating;
    String title;
    String description ;
    String usernameandtime;

    public ReviewPOI(double rating, String title, String description, String usernameandtime) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.usernameandtime = usernameandtime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsernameandtime() {
        return usernameandtime;
    }

    public void setUsernameandtime(String usernameandtime) {
        this.usernameandtime = usernameandtime;
    }
}
