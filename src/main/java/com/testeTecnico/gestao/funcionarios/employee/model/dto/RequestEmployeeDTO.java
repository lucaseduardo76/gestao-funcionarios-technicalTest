package com.testeTecnico.gestao.funcionarios.employee.model.dto;


import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmployeeDTO {

    @NotEmpty(message = "Name cannot be null")
    private String name;
    private EmployeeRole role;
    @NotEmpty(message = "You must inform the employee's salary")
    private Double salary;
    @NotEmpty(message = "You must have at least one registered phone number")
    private String firstPhone;
    private String secondPhone;
    private RequestAdressDTO adress;

}
