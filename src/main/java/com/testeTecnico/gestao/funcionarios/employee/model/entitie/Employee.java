package com.testeTecnico.gestao.funcionarios.employee.model.entitie;


import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private EmployeeRole role;
    private Double salary;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhoneNumber> phoneNumber;

    @OneToOne
    @JoinColumn(name = "adressId", referencedColumnName = "id")
    private Adress adress;

}
