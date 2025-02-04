package com.testeTecnico.gestao.funcionarios.employee.api.service;

import com.testeTecnico.gestao.funcionarios.employee.model.dto.RequestEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.ResponseEmployeeDTO;
import com.testeTecnico.gestao.funcionarios.employee.model.dto.UpdateEmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    ResponseEmployeeDTO save(RequestEmployeeDTO requestEmployeeDTO);
    List<ResponseEmployeeDTO> findAllEmployees();
    ResponseEmployeeDTO findById(String id);
    void update(UpdateEmployeeDTO updateEmployeeDTO);
    void delete(String id);
    void deleteAllEmployees();

}
