package com.testeTecnico.gestao.funcionarios.employee.api.service;

import com.testeTecnico.gestao.funcionarios.employee.exception.exception.NotFoundException;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.*;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Adress;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Employee;
import com.testeTecnico.gestao.funcionarios.employee.model.entitie.PhoneNumber;
import com.testeTecnico.gestao.funcionarios.employee.model.role.EmployeeRole;
import com.testeTecnico.gestao.funcionarios.employee.repository.AdressRepository;
import com.testeTecnico.gestao.funcionarios.employee.repository.EmployeeRepository;
import com.testeTecnico.gestao.funcionarios.employee.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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

        Adress adress = handleBuildAddress(requestEmployeeDTO.getAddress());
        Employee employee = handleBuildEmployee(requestEmployeeDTO, adress);
        handleBuildPhonenumber(requestEmployeeDTO.getFirstPhone(), employee);

        if(requestEmployeeDTO.getSecondPhone() != null)
            handleBuildPhonenumber(requestEmployeeDTO.getSecondPhone(), employee);


        log.info("[fim] EmployeeServiceApplication - save");
        return handleBuildResponseEmployee(employee);
    }


    @Override
    public List<ResponseEmployeeDTO> findAllEmployees() {
        log.info("[inicia] EmployeeServiceApplication - findAllEmployees");
        List<Employee> employees = employeeRepository.findAll();
        log.info("[fim] EmployeeServiceApplication - findAllEmployees");
        return employees.stream().map(this::handleBuildResponseEmployee).toList();
    }

    @Override
    public ResponseEmployeeDTO findById(String id) {
        log.info("[inicia] EmployeeServiceApplication - findById");
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Employee not found, check Id")
        );
        log.info("[fim] EmployeeServiceApplication - findById");
        return handleBuildResponseEmployee(employee);
    }

    @Override
    public void update(UpdateEmployeeDTO updateEmployeeDTO) {
        log.info("[inicia] EmployeeServiceApplication - update");

        Employee employee = employeeRepository.findById(updateEmployeeDTO.getId()).orElseThrow(
                () -> new NotFoundException("Employee not found, check Id")
        );


        if (employee != null) {
            employee.setPhoneNumber(phoneNumberRepository.findAllByOwnerId(employee.getId()));
            updateName(employee, updateEmployeeDTO.getName());
            updateRole(employee, updateEmployeeDTO.getRole());
            updateSalary(employee, updateEmployeeDTO.getSalary());
            updatePhoneNumbers(employee, updateEmployeeDTO);
            updateAdress(employee, updateEmployeeDTO.getAdress());


            employeeRepository.save(employee);
        }
        log.info("[fim] EmployeeServiceApplication - update");
    }

    @Override
    public void delete(String id) {
        log.info("[inicia] EmployeeServiceApplication - delete");
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Employee not found, check Id")
        );
        Adress adressToDelete = employee.getAdress();
        employeeRepository.delete(employee);
        handleDeleteAdress(adressToDelete);
        log.info("[fim] EmployeeServiceApplication - delete");
    }

    @Override
    public void deleteAllEmployees() {
        log.info("[inicia] EmployeeServiceApplication - deleteAllEmployees");
        employeeRepository.deleteAll();
        adressRepository.deleteAll();
        log.info("[fim] EmployeeServiceApplication - deleteAllEmployees");
    }

    private ResponseEmployeeDTO handleBuildResponseEmployee(Employee employee) {
        log.info("[inicia] EmployeeServiceApplication - handleBuildResponseEmployee");

        employee.setPhoneNumber(phoneNumberRepository.findAllByOwnerId(employee.getId()));

        ResponseEmployeeDTO responseEmployeeDTO = ResponseEmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .role(employee.getRole())
                .salary(employee.getSalary())
                .adress(employee.getAdress())
                .phoneNumber(employee.getPhoneNumber())
                .build();

        log.info("[fim] EmployeeServiceApplication - handleBuildResponseEmployee");
        return responseEmployeeDTO;

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

        return employeeRepository.save(employee);
    }

    private Adress handleBuildAddress(RequestAddressDTO requestAdressDTO){
        log.info("[inicia] EmployeeServiceApplication - handleBuildAddress");
        Adress adress = Adress.builder()
                .city(capitalizeName(requestAdressDTO.getCity()))
                .country(capitalizeName(requestAdressDTO.getCountry()))
                .street(capitalizeName(requestAdressDTO.getStreet()))
                .state((requestAdressDTO.getState()))
                .zip(formatNumber(requestAdressDTO.getZip()))
                .number(requestAdressDTO.getNumber())
                .build();
        log.info("[fim] EmployeeServiceApplication - handleBuildAddress");

        return adressRepository.save(adress);
    }

    private void handleBuildPhonenumber(String phoneNumber, Employee employee) {
        log.info("[inicia] EmployeeServiceApplication - handleBuildPhoneNumber");

        String formattedPhone = formatNumber(phoneNumber);

        PhoneNumber pn = PhoneNumber.builder()
                .number(formattedPhone)
                .owner(employee)
                .build();
        phoneNumberRepository.save(pn);

        log.info("[fim] EmployeeServiceApplication - handleBuildPhoneNumber");
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


    private void updateName(Employee employee, String name) {
        if (name != null) {
            employee.setName(name);
        }
    }

    private void updateRole(Employee employee, EmployeeRole role) {
        if (role != null) {
            employee.setRole(role);
        }
    }

    private void updateSalary(Employee employee, Double salary) {
        if (salary != null) {
            employee.setSalary(salary);
        }
    }

    private void updatePhoneNumbers(Employee employee, UpdateEmployeeDTO updateEmployeeDTO) {
        if (updateEmployeeDTO.getFirstPhone() != null && !employee.getPhoneNumber().isEmpty()) {
            employee.getPhoneNumber().get(0).setNumber(updateEmployeeDTO.getFirstPhone());
            phoneNumberRepository.save(employee.getPhoneNumber().get(0));
        }

        if (updateEmployeeDTO.getSecondPhone() != null) {
            if (employee.getPhoneNumber().size() > 1) {
                employee.getPhoneNumber().get(1).setNumber(updateEmployeeDTO.getSecondPhone());
                phoneNumberRepository.save(employee.getPhoneNumber().get(1));
            } else {
                addPhoneNumber(employee, updateEmployeeDTO.getSecondPhone());
            }
        }
    }

    private void addPhoneNumber(Employee employee, String phoneNumber) {
        PhoneNumber phone = new PhoneNumber();
        phone.setNumber(phoneNumber);
        phone.setOwner(employee);
        phoneNumberRepository.save(phone);
    }

    private void updateAdress(Employee employee, UpdateAdressDTO adressDTO) {
        if (adressDTO != null && employee.getAdress() != null) {
            Adress updatedAdress = adressDTO.toEntity(employee.getAdress());
            adressRepository.save(updatedAdress);
        }
    }

    private void handleDeleteAdress(Adress adress) {
        log.info("[fim] EmployeeServiceApplication - handleDeleteAdress");
        adressRepository.delete(adress);
        log.info("[fim] EmployeeServiceApplication - handleDeleteAdress");
    }

}
