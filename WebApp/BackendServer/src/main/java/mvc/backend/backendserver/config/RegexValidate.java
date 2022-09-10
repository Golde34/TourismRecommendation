package mvc.backend.backendserver.config;

public class RegexValidate {
    public static final String EMAIL_REGEX = "\\w+@\\w+(\\.\\w+){1,3}";
    public static final String PHONE_REGEX = "0[39]\\d{8,9}";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$";
}
