package com.testeTecnico.gestao.funcionarios.employee.repository;

import com.testeTecnico.gestao.funcionarios.employee.model.entitie.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {
    List<PhoneNumber> findAllByOwnerId(String id);
}
