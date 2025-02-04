package com.testeTecnico.gestao.funcionarios.employee.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdressDTO {

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String number;

}
