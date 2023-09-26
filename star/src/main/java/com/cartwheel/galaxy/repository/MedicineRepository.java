package com.cartwheel.galaxy.repository;

import com.cartwheel.galaxy.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    List<Medicine> findByMedicineNameContainingIgnoreCase(String medicineName);

    List<Medicine> findByExpiryDateBetweenAndQuantityInStockGreaterThan(LocalDate startDate, LocalDate endDate, int quantity);

    List<Medicine> findByContentContainingIgnoreCase(String content);

}
