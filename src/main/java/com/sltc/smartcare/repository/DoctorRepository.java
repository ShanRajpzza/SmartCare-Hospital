package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    // Custom query method to search doctors by the 'name' attribute inherited from the Person class (case-insensitive)
    List<Doctor> findByNameContainingIgnoreCase(String name);
    // Custom query method to retrieve a list of doctors assigned to a specific department ID
    List<Doctor> findByDepartment_DepartmentId(String departmentId);
}