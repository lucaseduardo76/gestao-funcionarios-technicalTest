package com.testeTecnico.gestao.funcionarios.employee.model.dto;

import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Adress;
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

    public Adress toEntity(Adress existingAdress) {
        if (existingAdress == null) {
            return null;
        }

        if (street != null) {
            existingAdress.setStreet(street);
        }
        if (city != null) {
            existingAdress.setCity(city);
        }
        if (state != null) {
            existingAdress.setState(state);
        }
        if (zip != null) {
            existingAdress.setZip(zip);
        }
        if (country != null) {
            existingAdress.setCountry(country);
        }
        if (number != null) {
            existingAdress.setNumber(number);
        }

        return existingAdress;
    }
}
