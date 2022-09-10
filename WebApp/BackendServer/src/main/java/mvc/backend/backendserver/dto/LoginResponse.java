package mvc.backend.backendserver.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final String message;

    public LoginResponse(String jwtToken, String message) {
        this.jwtToken = jwtToken;
        this.message = message;
    }
}
