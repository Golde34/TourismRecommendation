package mvc.backend.backendserver.dto;

import lombok.Data;

@Data
public class CityDTO {

    private int id;
    private String name;
    private int countryId;
}
