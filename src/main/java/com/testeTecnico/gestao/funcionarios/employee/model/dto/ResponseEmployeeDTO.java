package com.testeTecnico.gestao.funcionarios.employee.model.dto;

import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Adress;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.PhoneNumber;
import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEmployeeDTO {

    private UUID id;
    private String name;
    private EmployeeRole role;
    private Double salary;
    private List<PhoneNumber> phoneNumber;
    private Adress adress;
}
