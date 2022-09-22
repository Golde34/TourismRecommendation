package mvc.backend.backendserver.dto;

import lombok.Data;
import mvc.backend.backendserver.entity.Account;

@Data
public class TripGenerateDTO {
    private Account walletId;
    private String destination;
    private String budget;
    private String startDate;
    private String endDate;
}
