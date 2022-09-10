package mvc.backend.backendserver.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class TravelerRatingDTO {

    private int ratingId;
    private int rate;
    private String comment;
    private Date timeCreate;
    private int accountId;
    private int travelServiceId;
}
