package com.testeTecnico.gestao.funcionarios.employee.api.controller;

import com.testeTecnico.gestao.funcionarios.employee.model.dto.RequestEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.ResponseEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.UpdateEmployeeDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface EmployeeControllerInterface {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ResponseEmployeeDTO> insetEmployee(@RequestBody @Valid RequestEmployeeDTO requestEmployeeDTO);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ResponseEmployeeDTO>> findAllEmployees();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmployeeDTO> findEmployeeById(@PathVariable String id);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    public void deleteEmployee(@PathVariable String id);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/all")
    public void deleteAllEmployee();


}
