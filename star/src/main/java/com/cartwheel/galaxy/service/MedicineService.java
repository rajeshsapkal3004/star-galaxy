package com.cartwheel.galaxy.service;

import com.cartwheel.galaxy.entity.Medicine;

import java.util.List;

public interface MedicineService {

    public Medicine addNewMedicine(Medicine medicine);

    public List<Medicine> getAllMedicines();

    public Medicine getMedicineById(Long id);

    public List<Medicine> getExpiredMedicine();

    public List<Medicine> getExpiringMedicines();

    public Medicine updateMedicine(Long id, Medicine updatedMedicine);

    public String deleteMedicine(Long id);

    public List<Medicine> searchMedicine(String query);

}
