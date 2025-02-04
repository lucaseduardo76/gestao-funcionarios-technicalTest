package com.testeTecnico.gestao.funcionarios.employee.model.dto;

import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDTO {

    private String name;
    private EmployeeRole role;
    private Double salary;
    private String firstPhone;
    private String secondPhone;
    private UpdateAdressDTO adress;
}
