package com.testeTecnico.gestao.funcionarios.employee.repository;

import com.testeTecnico.gestao.funcionarios.employee.model.entitie.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdressRepository extends JpaRepository<Adress, UUID> {
}
