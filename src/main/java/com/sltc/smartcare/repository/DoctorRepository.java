package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    // Custom query to search doctors by name (case-insensitive)
    List<Doctor> findByDoctorNameContainingIgnoreCase(String name);

    // Custom query to find all doctors in a specific department
    List<Doctor> findByDepartment_DepartmentId(String departmentId);
}