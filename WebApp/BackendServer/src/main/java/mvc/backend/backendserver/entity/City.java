package mvc.backend.backendserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    private String cityName;

    @OneToMany(mappedBy = "city", orphanRemoval = true)
    @JsonIgnore
    private List<MyPOI> POIList;

    @OneToMany(mappedBy = "city", orphanRemoval = true)
    @JsonIgnore
    private List<RestingPlace> restingPlaceList;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}
