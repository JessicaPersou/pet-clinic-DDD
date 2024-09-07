package br.com.persou.petclinic.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    private Long id;
    private LocalDateTime date;
    private Pet pet;
    private Owner owner;
    private Veterinarian veterinarian;
}
