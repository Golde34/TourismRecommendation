package tr.mobileapp.Entity;

public class TripDetailRVModal {
    private String name;
    private String description;
    private double totalRating;
    private double price;
    private String location;
    private double openTime;
    private double closeTime;
    private String image;

    public TripDetailRVModal(String name, String description, double totalRating, double price, String location, double openTime, double closeTime, String image) {
        this.name = name;
        this.description = description;
        this.totalRating = totalRating;
        this.price = price;
        this.location = location;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getOpenTime() {
        return openTime;
    }

    public void setOpenTime(double openTime) {
        this.openTime = openTime;
    }

    public double getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(double closeTime) {
        this.closeTime = closeTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
