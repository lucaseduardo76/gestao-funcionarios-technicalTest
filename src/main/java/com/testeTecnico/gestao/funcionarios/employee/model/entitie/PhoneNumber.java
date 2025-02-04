package com.testeTecnico.gestao.funcionarios.employee.model.entitie;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_phone")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String number;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employeeId")
    private Employee owner;

}
