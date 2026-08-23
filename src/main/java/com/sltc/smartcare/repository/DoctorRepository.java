package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    // Spring Boot එකට දැන් හොයන්න ඕනේ 'name' කියන field එකෙන්
    List<Doctor> findByNameContainingIgnoreCase(String name);

    List<Doctor> findByDepartment_DepartmentId(String departmentId);
}