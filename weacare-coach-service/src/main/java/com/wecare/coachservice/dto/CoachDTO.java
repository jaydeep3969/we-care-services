package com.wecare.coachservice.dto;

import com.wecare.coachservice.entity.CoachEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CoachDTO {
	private String coachId;
	
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
	
	@NotNull(message="{coach.speciality.must}")
	@Size(min=3, max=50, message="{coach.speciality.invalidLength}")
	private String speciality;
	
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
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
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public static CoachEntity createCoachEntity(CoachDTO coachDTO) {
		CoachEntity coachEntity = new CoachEntity();
		coachEntity.setName(coachDTO.getName());
		coachEntity.setPassword(coachDTO.getPassword());
		coachEntity.setGender(coachDTO.getGender());
		coachEntity.setDateOfBirth(coachDTO.getDateOfBirth());
		coachEntity.setMobileNumber(coachDTO.getMobileNumber());
		coachEntity.setSpeciality(coachDTO.getSpeciality());

		return coachEntity;
	}
}
