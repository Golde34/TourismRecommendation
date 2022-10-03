package mvc.backend.backendserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "POI")

public class MyPOI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POI_id")
    private int POIId;

    @Column(name = "POI_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "total_rating")
    private double totalRating;

    @Column(name = "price")
    private double price;

    @Column(name = "location")
    private String location;

    @Column(name = "open_time")
    private double openTime;

    @Column(name = "close_time")
    private double closeTime;

    @Column(name = "duration")
    private double duration;

    @Column(name="thumbnail")
    private  String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPOIId() {
        return POIId;
    }

    public void setPOIId(int POIId) {
        this.POIId = POIId;
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



    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;


    @OneToMany(mappedBy = "startStation")
    @JsonIgnore
    private List<Distance> startStation;

    @OneToMany(mappedBy = "endStation")
    @JsonIgnore
    private List<Distance> endStation;
}
