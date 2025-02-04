package com.testeTecnico.gestao.funcionarios.employee.model.entitie;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_phone")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee owner;

}
