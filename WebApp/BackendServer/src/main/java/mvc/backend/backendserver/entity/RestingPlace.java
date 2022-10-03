package mvc.backend.backendserver.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "resting_place")
public class RestingPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_id")
    private int restId;

    @Column(name = "rest_place_name")
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

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
