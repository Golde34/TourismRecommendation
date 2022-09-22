package mvc.backend.backendserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;

    private String countryName;

    @OneToMany(mappedBy = "country", orphanRemoval = true)
    @JsonIgnore
    private List<City> cities;
}
