package tr.mobileapp.Entity;

public class MyPOI {
    private int POIId;
    private String name;
    private String description;
    private double totalRating;
    private double price;
    private String location;
    private double openTime;
    private double closeTime;
    private double duration;
    private  String thumbnail;
    private String cityName;
    private String countryName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPOIId() {
        return POIId;
    }

    public void setPOIId(int POIId) {
        this.POIId = POIId;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
