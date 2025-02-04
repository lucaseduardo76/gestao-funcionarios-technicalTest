package com.testeTecnico.gestao.funcionarios.employee.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddressDTO {

    @NotEmpty(message = "street cannot be empty")
    private String street;
    @NotEmpty(message = "city cannot be empty")
    private String city;
    @NotEmpty(message = "state cannot be empty")
    private String state;
    @NotEmpty(message = "zip cannot be empty")
    private String zip;
    @NotEmpty(message = "country cannot be empty")
    private String country;
    @NotEmpty(message = "number cannot be empty")
    private String number;
}
