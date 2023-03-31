package com.wecare.userservice.entity;

import com.wecare.userservice.dto.UserDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="UserTable")
@GenericGenerator(name = "user_id_generator", strategy = "com.wecare.userservice.utility.UserIdGenerator")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "user_id_generator")
    @Column(name="user_id")
    private String userId;
    private String name;
    private String password;
    private char gender;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="mobile_number")
    private long mobileNumber;
    private String email;
    private int pincode;
    private String city;
    private String state;
    private String country;

    public UserEntity() {
    }

    public UserEntity(String userId, String name, String password, char gender, LocalDate dateOfBirth, long mobileNumber, String email, int pincode, String city, String state, String country) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static UserDTO prepareUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(userEntity.getUserId());
        userDTO.setName(userEntity.getName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setGender(userEntity.getGender());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setDateOfBirth(userEntity.getDateOfBirth());
        userDTO.setMobileNumber(userEntity.getMobileNumber());
        userDTO.setCity(userEntity.getCity());
        userDTO.setState(userEntity.getState());
        userDTO.setCountry(userEntity.getCountry());
        userDTO.setPin(userEntity.getPincode());

        return userDTO;
    }
}
