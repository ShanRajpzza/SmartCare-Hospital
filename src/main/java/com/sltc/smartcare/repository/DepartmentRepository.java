package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Handles basic CRUD operations for the Department entity
@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
}