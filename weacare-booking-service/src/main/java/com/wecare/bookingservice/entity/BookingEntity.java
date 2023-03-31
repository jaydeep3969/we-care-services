package com.wecare.bookingservice.entity;

import com.wecare.bookingservice.dto.BookingDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="BookingTable")
@GenericGenerator(name = "booking_id_generator", strategy = "com.infosys.utility.BookingIdGenerator")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "coach_id")
    private String coachId;

    private String slot;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public static BookingDTO prepareBookingDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setBookingId(bookingEntity.getBookingId());
        bookingDTO.setUserId(bookingEntity.getUserId());
        bookingDTO.setCoachId(bookingEntity.getCoachId());
        bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
        bookingDTO.setSlot(bookingEntity.getSlot());

        return bookingDTO;
    }
}
