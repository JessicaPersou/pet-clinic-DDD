package br.com.persou.petclinic.domain.repository;

import br.com.persou.petclinic.domain.model.Appointment;
import java.time.LocalDateTime;


public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    boolean isTimeSlotAvailable(LocalDateTime dateTime);
}


