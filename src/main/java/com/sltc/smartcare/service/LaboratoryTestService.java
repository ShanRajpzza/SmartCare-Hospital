package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.LaboratoryTest;
import com.sltc.smartcare.repository.LaboratoryTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LaboratoryTestService {

    @Autowired
    private LaboratoryTestRepository repository;

    private void validateLabTest(LaboratoryTest test) {
        // name can not be empty
        if (test.getTestName() == null || test.getTestName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Test Name cannot be empty!");
        }

        // minus values are not accptable
        if (test.getTestCharge() != null && test.getTestCharge().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Test Charge cannot be a negative value!");
        }

        // test date can not be a future date
        if (test.getTestDate() != null && test.getTestDate().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Test Date cannot be in the future!");
        }
    }

    public LaboratoryTest createLabTest(LaboratoryTest test) {
        if (repository.existsById(test.getLabTestId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Lab Test with ID " + test.getLabTestId() + " already exists!");
        }
        validateLabTest(test);
        return repository.save(test);
    }

    public List<LaboratoryTest> getAllLabTests() {
        return repository.findAll();
    }

    public LaboratoryTest getLabTestById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lab Test not found with ID: " + id));
    }

    public LaboratoryTest updateLabTest(String id, LaboratoryTest updatedTest) {
        LaboratoryTest existingTest = getLabTestById(id);

        validateLabTest(updatedTest);

        existingTest.setTestName(updatedTest.getTestName());
        existingTest.setTestDate(updatedTest.getTestDate());
        existingTest.setTestResult(updatedTest.getTestResult());
        existingTest.setTestStatus(updatedTest.getTestStatus());
        existingTest.setTechnicianName(updatedTest.getTechnicianName());
        existingTest.setTestCharge(updatedTest.getTestCharge());
        existingTest.setPatientId(updatedTest.getPatientId());
        existingTest.setDoctorId(updatedTest.getDoctorId());

        return repository.save(existingTest);
    }

    public void deleteLabTest(String id) {
        LaboratoryTest existingTest = getLabTestById(id);
        repository.delete(existingTest);
    }
}