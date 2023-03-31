package com.wecare.coachservice.entity;

import com.wecare.coachservice.dto.CoachDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name="CoachTable")
@GenericGenerator(name = "coach_id_generator", strategy = "com.wecare.coachservice.utility.CoachIdGenerator")
public class CoachEntity {
    @Id
    @GeneratedValue(generator = "coach_id_generator")
    @Column(name="coach_id")
    private String coachId;

    private String name;

    private String password;

    private char gender;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="mobile_number")
    private long mobileNumber;

    private String speciality;

    public CoachEntity() {}

    public CoachEntity(String coachId, String name, String password, char gender, LocalDate dateOfBirth, long mobileNumber, String speciality) {
        this.coachId = coachId;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.speciality = speciality;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public static Optional<CoachDTO> prepareCoachDTO(Optional<CoachEntity> coach) {
        if(coach.isEmpty()) {
            return Optional.empty();
        }

        CoachEntity coachEntity = coach.get();

        CoachDTO coachDTO = new CoachDTO();

        coachDTO.setCoachId(coachEntity.getCoachId());
        coachDTO.setName(coachEntity.getName());
        coachDTO.setPassword(coachEntity.getPassword());
        coachDTO.setGender(coachEntity.getGender());
        coachDTO.setDateOfBirth(coachEntity.getDateOfBirth());
        coachDTO.setMobileNumber(coachEntity.getMobileNumber());
        coachDTO.setSpeciality(coachEntity.getSpeciality());

        return Optional.of(coachDTO);
    }
}
