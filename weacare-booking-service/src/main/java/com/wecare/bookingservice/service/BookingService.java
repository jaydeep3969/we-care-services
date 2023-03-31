package com.wecare.bookingservice.service;

import com.wecare.bookingservice.dto.BookingDTO;
import com.wecare.bookingservice.entity.BookingEntity;
import com.wecare.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    private Environment environment;

    public String bookAppointment(String userId, String coachId, LocalDate appointmentDate, String slot) {
        this.bookingRepository.creteBooking(userId, coachId, appointmentDate, slot);
//        return environment.getProperty(com.wecare.coachservice.utility.ActionConstants.Appon)
        return "Appointment Scheduled Successfully.";
    }

    public BookingDTO getBooking(Integer bookingId) {
        return BookingEntity.prepareBookingDTO(this.bookingRepository.findByBookingId(bookingId));
    }

    public String rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, String slot) {
        this.bookingRepository.updateBooking(bookingId, appointmentDate, slot);
        return "Appointment rescheduled successfully.";
    }

    public String cancelAppointment(Integer bookingId) {
        this.bookingRepository.deleteBooking(bookingId);
        return "Appointment cancelled successfully";
    }

    public List<BookingDTO> getAllAppointmentsForUser(String userId) {
        List<BookingEntity> bookingEntities = this.bookingRepository.findByUserId(userId);

        List<BookingDTO> bookingDTOs = bookingEntities.stream().map(bookingEntity -> BookingEntity.prepareBookingDTO(bookingEntity)).collect(Collectors.toList());

        return bookingDTOs;
    }

    public List<BookingDTO> getAllAppointmentsForUserByDate(String userId, LocalDate appointmentDate) {
        List<BookingEntity> bookingEntities = this.bookingRepository.findByUserIdAndAppointmentDate(userId, appointmentDate);

        List<BookingDTO> bookingDTOs = bookingEntities.stream().map(bookingEntity -> BookingEntity.prepareBookingDTO(bookingEntity)).collect(Collectors.toList());

        return bookingDTOs;
    }

    public List<BookingDTO> getAllAppointmentsForCoach(String coachId) {
        List<BookingEntity> bookingEntities = this.bookingRepository.findByCoachId(coachId);

        List<BookingDTO> bookingDTOs = bookingEntities.stream().map(bookingEntity -> BookingEntity.prepareBookingDTO(bookingEntity)).collect(Collectors.toList());

        return bookingDTOs;
    }

    public List<BookingDTO> getAllAppointmentsForCoachByDate(String coachId, LocalDate appointmentDate) {
        List<BookingEntity> bookingEntities = this.bookingRepository.findByCoachIdAndAppointmentDate(coachId, appointmentDate);

        List<BookingDTO> bookingDTOs = bookingEntities.stream().map(bookingEntity -> BookingEntity.prepareBookingDTO(bookingEntity)).collect(Collectors.toList());

        return bookingDTOs;
    }


}
