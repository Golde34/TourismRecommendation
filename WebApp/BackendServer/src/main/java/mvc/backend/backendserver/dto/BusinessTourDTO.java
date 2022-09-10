package mvc.backend.backendserver.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class BusinessTourDTO {
    private int tourId;
    private Date startDate;
    private Date endDate;
}
