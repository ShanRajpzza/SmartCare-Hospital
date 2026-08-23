package com.sltc.smartcare.service;

import com.sltc.smartcare.entity.Medicine;
import com.sltc.smartcare.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    //Data Validation Method
    private void validateMedicine(Medicine medicine) {
        // checking medicine name
        if (medicine.getName() == null || medicine.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Medicine name cannot be empty!");
        }

        // price can not be negative value
        if (medicine.getPrice() != null && medicine.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Price cannot be a negative value!");
        }
    }

    // Create
    public Medicine createMedicine(Medicine medicine) {
        if (repository.existsById(medicine.getMedicineId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error: Medicine with ID " + medicine.getMedicineId() + " already exists!");
        }
        validateMedicine(medicine);
        return repository.save(medicine);
    }

    // Read All
    public List<Medicine> getAllMedicines() {
        return repository.findAll();
    }

    // Read by ID
    public Medicine getMedicineById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found with ID: " + id));
    }

    // Update
    public Medicine updateMedicine(String id, Medicine updatedMedicine) {
        Medicine existingMedicine = getMedicineById(id);

        validateMedicine(updatedMedicine);

        existingMedicine.setName(updatedMedicine.getName());
        existingMedicine.setPrice(updatedMedicine.getPrice());

        return repository.save(existingMedicine);
    }

    // Delete
    public void deleteMedicine(String id) {
        Medicine existingMedicine = getMedicineById(id);
        repository.delete(existingMedicine);
    }
}