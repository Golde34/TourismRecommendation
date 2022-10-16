package tr.mobileapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.sql.Date;

import tr.mobileapp.Database.Converters;

@Entity
public class Account {

    //    @PrimaryKey(autoGenerate = true)
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "account_id")
    private String id;

    @ColumnInfo(name = "username")
    @NonNull
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "date_of_birth")
    private Date DOB;

    @ColumnInfo(name = "email")
    @NonNull
    private String email;

    @ColumnInfo(name = "full_name")
    @NonNull
    private String fullName;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "phone_number")
    @NonNull
    private String phoneNumber;

    @ColumnInfo(name = "level")
    private Integer level;

    @ColumnInfo(name = "level_point")
    private Integer levelPoint;

    @ColumnInfo()
    private Integer status;

    @ColumnInfo()
    @TypeConverters(Converters.class)
    private Role role;

    public Account(String id, @NonNull String username, String password, String address, Date DOB, @NonNull String email, @NonNull String fullName,
                   String gender, String image, @NonNull String phoneNumber, Integer level, Integer levelPoint, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.DOB = DOB;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.level = level;
        this.levelPoint = levelPoint;
        this.status = status;
    }

    public Account(String id, String accountName) {
        this.id = id;
        this.username = accountName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}