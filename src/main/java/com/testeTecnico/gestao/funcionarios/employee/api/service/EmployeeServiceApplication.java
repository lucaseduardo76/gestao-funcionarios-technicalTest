package com.testeTecnico.gestao.funcionarios.employee.api.service;

import com.testeTecnico.gestao.funcionarios.employee.model.dto.RequestAdressDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.RequestEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.ResponseEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.UpdateEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Adress;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Employee;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.PhoneNumber;
import com.testeTecnico.gestao.funcionarios.employee.repository.AdressRepository;
import com.testeTecnico.gestao.funcionarios.employee.repository.EmployeeRepository;
import com.testeTecnico.gestao.funcionarios.employee.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmployeeServiceApplication implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AdressRepository adressRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    @Override
    public ResponseEmployeeDTO save(RequestEmployeeDTO requestEmployeeDTO) {
        log.info("[inicia] EmployeeServiceApplication - save");

        Adress adress = handleBuildAdrees(requestEmployeeDTO.getAdress());

        Employee employee = handleBuildEmployee(requestEmployeeDTO, adress);

        handleBuildPhonenumber(requestEmployeeDTO.getFirstPhone(), employee);

        if(requestEmployeeDTO.getSecondPhone() != null)
            handleBuildPhonenumber(requestEmployeeDTO.getSecondPhone(), employee);


        log.info("[fim] EmployeeServiceApplication - save");
        return null;
    }

    @Override
    public List<ResponseEmployeeDTO> findAllEmployees() {
        return List.of();
    }

    @Override
    public ResponseEmployeeDTO findById(UUID id) {
        return null;
    }

    @Override
    public void update(UpdateEmployeeDTO updateEmployeeDTO) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void deleteAllEmployees() {

    }

    private Employee handleBuildEmployee(RequestEmployeeDTO requestEmployeeDTO, Adress adress) {
        log.info("[inicia] EmployeeServiceApplication - handleBuildEmployee");
        Employee employee = Employee.builder()
                .name(capitalizeName(requestEmployeeDTO.getName()))
                .role(requestEmployeeDTO.getRole())
                .salary(requestEmployeeDTO.getSalary())
                .adress(adress)
                .build();
        log.info("[fim] EmployeeServiceApplication - handleBuildEmployee");
        return employee;
    }

    private Adress handleBuildAdrees(RequestAdressDTO requestAdressDTO){
        log.info("[inicia] EmployeeServiceApplication - handleBuildAdrees");
        Adress adress = Adress.builder()
                .city(requestAdressDTO.getCity())
                .country(requestAdressDTO.getCountry())
                .street(requestAdressDTO.getStreet())
                .state(requestAdressDTO.getState())
                .zip(formatNumber(requestAdressDTO.getZip()))
                .number(requestAdressDTO.getNumber())
                .build();
        log.info("[fim] EmployeeServiceApplication - handleBuildAdrees");

        return adressRepository.save(adress);
    }

    private void handleBuildPhonenumber(String phoneNumber, Employee employee) {
        log.info("[inicia] EmployeeServiceApplication - handleBuildPhonenumber");

        String formattedPhone = formatNumber(phoneNumber);

        PhoneNumber pn = PhoneNumber.builder()
                .number(formattedPhone)
                .owner(employee)
                .build();
        phoneNumberRepository.save(pn);

        log.info("[fim] EmployeeServiceApplication - handleBuildPhonenumber");
    }

    private String formatNumber(String phoneNumber) {
        if (phoneNumber == null) return "";
        return phoneNumber.replaceAll("[^0-9]", "");
    }

    private String capitalizeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        return Arrays.stream(name.trim().split("\\s+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

}
