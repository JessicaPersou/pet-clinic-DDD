package br.com.persou.petclinic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinarian {
    private Long id;
    private String name;
    private String specialization;
}
