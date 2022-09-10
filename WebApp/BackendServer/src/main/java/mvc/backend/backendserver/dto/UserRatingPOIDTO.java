package mvc.backend.backendserver.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserRatingPOIDTO {

    private int ratingId;
    private int rate;
    private String comment;
    private Date timeCreate;
    private int accountId;
    private int poiId;
}
