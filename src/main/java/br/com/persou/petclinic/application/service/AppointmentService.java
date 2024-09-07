package br.com.persou.petclinic.application.service;

import br.com.persou.petclinic.domain.model.Appointment;
import br.com.persou.petclinic.infrastructure.persistence.JpaAppointmentRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AppointmentService {
    private final JpaAppointmentRepository appointmentRepository;

    public AppointmentService(JpaAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Appointment createAppointment(Appointment appointment) {
//        validateAppointment(appointment);
//        checkAvailability(appointment.getDate());
        return appointmentRepository.save(appointment);
    }

    private void validateAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }
        if (appointment.getPet().getName() == null || appointment.getPet().getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name is required");
        }
        if (appointment.getOwner().getName() == null || appointment.getOwner().getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name is required");
        }
        if (appointment.getDate() == null) {
            throw new IllegalArgumentException("Appointment date and time are required");
        }
        if (appointment.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date and time must be in the future");
        }
    }

    private void checkAvailability(LocalDateTime dateTime) {
        boolean isAvailable = appointmentRepository.isTimeSlotAvailable(dateTime);
        if (!isAvailable) {
            throw new IllegalStateException("The selected time slot is not available");
        }
    }
}
