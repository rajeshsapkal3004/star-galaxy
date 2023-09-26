package com.cartwheel.galaxy.service.impl;

import com.cartwheel.galaxy.commonResponse.CommonResposneForMedicine;
import com.cartwheel.galaxy.entity.Medicine;
import com.cartwheel.galaxy.repository.MedicineRepository;
import com.cartwheel.galaxy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineInventoryRepository;


    @Override
    public Medicine addNewMedicine(Medicine medicine) {
        Medicine savedMedicine = medicineInventoryRepository.save(medicine);
        return savedMedicine;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return null;
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return null;
    }

    @Override
    public List<Medicine> getExpiredMedicine() {
        return null;
    }

    @Override
    public List<Medicine> getExpiringMedicines() {
        return null;
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
        return null;
    }

    @Override
    public String deleteMedicine(Long id) {
        return null;
    }

    @Override
    public List<Medicine> searchMedicine(String query) {
        return null;
    }
}
