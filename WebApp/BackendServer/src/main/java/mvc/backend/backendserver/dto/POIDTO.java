package mvc.backend.backendserver.dto;

import lombok.Data;

@Data
public class POIDTO {

    private int POIId;
    private String name;
    private String description;
    private double totalRating;
    private double price;
    private String location;
    private int cityId;
    private int dayOfWeek;
}
