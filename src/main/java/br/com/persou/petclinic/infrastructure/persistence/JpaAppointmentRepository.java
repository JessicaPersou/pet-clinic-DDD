package br.com.persou.petclinic.infrastructure.persistence;

import br.com.persou.petclinic.domain.model.Appointment;
import br.com.persou.petclinic.domain.repository.AppointmentRepository;
import br.com.persou.petclinic.infrastructure.mapper.AppointmentMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAppointmentRepository implements AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AppointmentMapper appointmentMapper;

    public JpaAppointmentRepository(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity entity = appointmentMapper.toEntity(appointment);
        entityManager.merge(entity);
        return appointmentMapper.toDomain(entity);
    }

    @Override
    public boolean isTimeSlotAvailable(LocalDateTime dateTime) {
        String jpql = "SELECT COUNT(a) FROM AppointmentEntity a WHERE a.dateTime = :dateTime";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("dateTime", dateTime)
                .getSingleResult();
        return count == 0;
    }
}