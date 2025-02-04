package com.testeTecnico.gestao.funcionarios.employee.model.entitie;


import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "adress")
@Data
@Builder
@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private EmployeeRole role;
    private Double salary;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneNumber> phoneNumber = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "adressId", referencedColumnName = "id")
    private Adress adress;

}
