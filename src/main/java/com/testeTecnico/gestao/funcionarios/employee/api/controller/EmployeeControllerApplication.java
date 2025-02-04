package com.testeTecnico.gestao.funcionarios.employee.api.controller;

import com.testeTecnico.gestao.funcionarios.employee.api.service.EmployeeService;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.RequestEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.ResponseEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.UpdateEmployeeDTO;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Log4j2
public class EmployeeControllerApplication implements EmployeeControllerInterface{

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<ResponseEmployeeDTO> insetEmployee(RequestEmployeeDTO requestEmployeeDTO) {
        log.info("[inicia] EmployeControllerApplication - insetEmployee");

        var employee = employeeService.save(requestEmployeeDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        log.info("[fim] EmployeControllerApplication - insetEmployee");
        return ResponseEntity.created(uri).body(employee);
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findAllEmployees() {
        log.info("[inicia] EmployeControllerApplication - findAllEmployees");
        var employees = employeeService.findAllEmployees();
        log.info("[fim] EmployeControllerApplication - findAllEmployees");

        return ResponseEntity.ok().body(employees);
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> findEmployeeById(UUID id) {
        log.info("[inicia] EmployeControllerApplication - findEmployeeById");
        var employee = employeeService.findById(id);
        log.info("[fim] EmployeControllerApplication - findEmployeeById");
        return ResponseEntity.ok().body(employee);
    }

    @Override
    public void updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        log.info("[inicia] EmployeControllerApplication - updateEmployee");
        employeeService.update(updateEmployeeDTO);
        log.info("[fim] EmployeControllerApplication - updateEmployee");

    }

    @Override
    public void deleteEmployee(UUID id) {
        log.info("[inicia] EmployeControllerApplication - deleteEmployee");
        employeeService.delete(id);
        log.info("[fim] EmployeControllerApplication - deleteEmployee");
    }

    @Override
    public void deleteAllEmployee() {
        log.info("[inicia] EmployeControllerApplication - deleteAllEmployee");
        employeeService.deleteAllEmployees();
        log.info("[fim] EmployeControllerApplication - deleteAllEmployee");
    }
}
