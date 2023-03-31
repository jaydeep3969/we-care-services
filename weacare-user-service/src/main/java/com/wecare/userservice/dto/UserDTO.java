package com.wecare.userservice.dto;

import com.wecare.userservice.entity.UserEntity;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserDTO {
	private String userId;
	
	@NotNull(message="{password.must}")
	@Size(min=5, max=10, message="{password.invalidLength}")
	private String password;
	
	@NotNull(message="{name.must}")
	@Size(min=3, max=50, message="{name.invalidLength}")
	private String name;
	
	private LocalDate dateOfBirth;
	private char gender;
	
	@NotNull(message="{mobileNo.must}")
	@Min(value = 1000000000L, message = "{mobileNo.invalid}")
	@Max(value = 9999999999L, message = "{mobileNo.invalid}")
	private long mobileNumber;
	
	@Email(message="{user.email.invalid")
	private String email;

	@Min(value = 100000, message = "{user.pin.invalid}")
	@Max(value = 999999, message = "{user.pin.invalid}")
	private int pin;
	
	@NotNull(message="{user.city.must}")
	@Size(min=3, max=20, message="{user.city.invalidLength}")
	private String city;
	
	@NotNull(message="{user.state.must}")
	@Size(min=3, max=20, message="{user.state.invalidLength}")
	private String state;
	
	@NotNull(message="{user.country.must}")
	@Size(min=3, max=20, message="{user.country.invalidLength}")
	private String country;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
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
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
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

	@Override
	public String toString() {
		return "UserDTO{" +
				"userId='" + userId + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", gender=" + gender +
				", mobileNumber=" + mobileNumber +
				", email='" + email + '\'' +
				", pin=" + pin +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				'}';
	}

	public static UserEntity prepareUserEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();

		userEntity.setName(userDTO.getName());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setGender(userDTO.getGender());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setDateOfBirth(userDTO.getDateOfBirth());
		userEntity.setMobileNumber(userDTO.getMobileNumber());
		userEntity.setCity(userDTO.getCity());
		userEntity.setState(userDTO.getState());
		userEntity.setCountry(userDTO.getCountry());
		userEntity.setPincode(userDTO.getPin());

		return userEntity;
	}
}
