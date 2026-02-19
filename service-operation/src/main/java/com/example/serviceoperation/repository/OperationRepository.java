package com.example.serviceoperation.repository;

import com.example.serviceoperation.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
