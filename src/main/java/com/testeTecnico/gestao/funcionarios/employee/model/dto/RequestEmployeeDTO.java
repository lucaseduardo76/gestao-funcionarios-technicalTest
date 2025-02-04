package com.testeTecnico.gestao.funcionarios.employee.model.dto;


import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "You must inform the employee's salary")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;
    @NotEmpty(message = "You must have at least one registered phone number")
    private String firstPhone;
    private String secondPhone;
    private @Valid RequestAddressDTO address;

}
