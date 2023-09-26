package com.cartwheel.galaxy.controller;

import com.cartwheel.galaxy.commonMapper.Mapper;
import com.cartwheel.galaxy.commonResponse.CommonResposneForMedicine;
import com.cartwheel.galaxy.dto.MedicineDto;
import com.cartwheel.galaxy.entity.Medicine;
import com.cartwheel.galaxy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/star-galaxy/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/add-medicine")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> addMedicine(@RequestBody MedicineDto medicine) {
        CommonResposneForMedicine commonResponse = new CommonResposneForMedicine();
        LocalDate currentDate = LocalDate.now();
        if (medicine.getManufactureDate().isAfter(currentDate)) {
            commonResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            commonResponse.setMessage("Production date cannot be in the future");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
        } else if (medicine.getExpiryDate().isBefore(medicine.getManufactureDate())) {
            commonResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            commonResponse.setMessage("Expiry date cannot be less than Production Date");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
        }else{
            Medicine medicine1 = mapper.convertMedicineDtoToMedicine(medicine);
            Medicine savedMedicine = medicineService.addNewMedicine(medicine1);
            return new ResponseEntity<>(savedMedicine, HttpStatus.CREATED);
        }
    }


}
