package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // නම අනුව Search කිරීමට මෙම line එක අත්‍යවශ්‍ය වේ
    List<Patient> findByNameContainingIgnoreCase(String name);
}