package com.sltc.smartcare.repository;

import com.sltc.smartcare.entity.LaboratoryTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, String> {
}