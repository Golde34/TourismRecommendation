package mvc.backend.backendserver.service.interfaces;



import mvc.backend.backendserver.dto.AccountDTO;
import mvc.backend.backendserver.dto.RegisterResponse;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.entity.RatingPOI;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountService {
    public Account login(String username, String password);
    public String signUp(AccountDTO accountDTO);

}
