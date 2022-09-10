package mvc.backend.backendserver.dto;

import mvc.backend.backendserver.config.RegexValidate;
import lombok.Data;
import mvc.backend.backendserver.config.RegexValidate;
import org.aspectj.bridge.Message;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;

@Data
public class AccountDTO {
    private String id;
    private String address;
    private Date DOB;

    @NotBlank(message = "Email is require.")
    @Pattern(regexp = RegexValidate.EMAIL_REGEX, message = "Invalid email")
    private String email;

    @NotBlank(message = "Must enter your full name.")
    private String fullName;

    private String gender;

    @NotBlank(message = "Must enter your identity card.")
    private String identityCard;

    private String image;

    @NotBlank(message = "Password is require.")
    @Pattern(regexp = RegexValidate.PASSWORD_REGEX, message = "Invalid password")
    private String password;

    @NotBlank(message = "Must enter your phone number.")
    @Pattern(regexp = RegexValidate.PHONE_REGEX, message = "Invalid phone number")
    private String phoneNumber;

    private Date registerDate;
    private Integer level;
    private Integer levelPoint;
    private int status;
    private int roleId;

    @NotBlank(message = "Username is require.")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevelPoint() {
        return levelPoint;
    }

    public void setLevelPoint(Integer levelPoint) {
        this.levelPoint = levelPoint;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
