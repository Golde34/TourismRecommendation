package mvc.backend.backendserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import mvc.backend.backendserver.dto.CityDTO;

import javax.persistence.*;
import java.util.List;

@Data
public class TravelServiceDTO {

    private int id;
    private String name;
    private double totalRating;
    private String location;
    private String description;
    private int cityId;
    private int travelServiceType;
}
