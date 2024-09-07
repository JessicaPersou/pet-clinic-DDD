package br.com.persou.petclinic.infrastructure.mapper;
import br.com.persou.petclinic.domain.model.Appointment;
import br.com.persou.petclinic.domain.model.Owner;
import br.com.persou.petclinic.domain.model.Pet;
import br.com.persou.petclinic.domain.model.Veterinarian;
import br.com.persou.petclinic.infrastructure.persistence.AppointmentEntity;
import br.com.persou.petclinic.infrastructure.persistence.OwnerEntity;
import br.com.persou.petclinic.infrastructure.persistence.PetEntity;
import br.com.persou.petclinic.infrastructure.persistence.VeterinarianEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentEntity toEntity(Appointment appointment) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(appointment.getId());

        // Mapeamento do OwnerEntity
        OwnerEntity owner = new OwnerEntity();
        owner.setId(appointment.getOwner().getId());
        owner.setName(appointment.getOwner().getName());
        owner.setAddress(appointment.getOwner().getAddress());
        owner.setPhoneNumber(appointment.getOwner().getPhoneNumber());

        // Mapeamento do PetEntity
        PetEntity pet = new PetEntity();
        pet.setId(appointment.getPet().getId());
        pet.setName(appointment.getPet().getName());
        pet.setBreed(appointment.getPet().getBreed());
        pet.setOwner(owner);  // Define o dono do pet

        // Se existir um veterinário, mapeie-o também
        VeterinarianEntity veterinarian = new VeterinarianEntity();
        veterinarian.setId(appointment.getVeterinarian().getId());
        veterinarian.setName(appointment.getVeterinarian().getName());
        veterinarian.setSpecialization(appointment.getVeterinarian().getSpecialization());

        // Setar os campos da entidade Appointment
        entity.setPet(pet);
        entity.setOwner(owner);
        entity.setVeterinarian(veterinarian);
        entity.setDateTime(appointment.getDate());

        return entity;
    }

    public Appointment toDomain(AppointmentEntity entity) {
        Appointment appointment = new Appointment();
        appointment.setId(entity.getId());

        // Mapeamento do Owner (domínio)
        Owner owner = new Owner();
        owner.setId(entity.getOwner().getId());
        owner.setName(entity.getOwner().getName());
        owner.setAddress(entity.getOwner().getAddress());
        owner.setPhoneNumber(entity.getOwner().getPhoneNumber());

        // Mapeamento do Pet (domínio)
        Pet pet = new Pet();
        pet.setId(entity.getPet().getId());
        pet.setName(entity.getPet().getName());
        pet.setBreed(entity.getPet().getBreed());
        pet.setOwner(owner);  // Define o dono do pet

        // Mapeamento do Veterinarian (domínio)
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setId(entity.getVeterinarian().getId());
        veterinarian.setName(entity.getVeterinarian().getName());
        veterinarian.setSpecialization(entity.getVeterinarian().getSpecialization());

        // Setar os campos do Appointment
        appointment.setPet(pet);
        appointment.setOwner(owner);
        appointment.setVeterinarian(veterinarian);
        appointment.setDate(entity.getDateTime());

        return appointment;
    }
}
