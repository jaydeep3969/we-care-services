package com.wecare.bookingservice.repository;

import com.wecare.bookingservice.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO booking_table (booking_id, user_id, coach_id, appointment_date, slot) VALUES(0, :userId, :coachId, :appointmentDate, :slot)", nativeQuery = true)
    public void creteBooking(String userId, String coachId, LocalDate appointmentDate, String slot);

    public BookingEntity findByBookingId(Integer bookingId);

    public List<BookingEntity> findByUserId(String userId);

    public List<BookingEntity> findByUserIdAndAppointmentDate(String userId, LocalDate appointmentDate);

    public List<BookingEntity> findByCoachId(String coachId);

    public List<BookingEntity> findByCoachIdAndAppointmentDate(String coachId, LocalDate appointmentDate);

    @Modifying
    @Transactional
    @Query("update BookingEntity b set b.appointmentDate = :appointmentDate, b.slot = :slot where b.bookingId = :bookingId")
    public void updateBooking(Integer bookingId, LocalDate appointmentDate, String slot);

    @Modifying
    @Transactional
    @Query("delete from BookingEntity b where b.bookingId = :bookingId")
    public void deleteBooking(Integer bookingId);
}
