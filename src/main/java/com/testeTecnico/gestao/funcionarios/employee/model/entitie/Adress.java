package com.testeTecnico.gestao.funcionarios.employee.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "owner")
@Data
@Builder
@Entity
@Table(name = "tb_adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String number;

    @JsonIgnore
    @OneToOne(mappedBy = "adress")
    private Employee owner;
}
