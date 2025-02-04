package com.testeTecnico.gestao.funcionarios.employee.repository;

import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
