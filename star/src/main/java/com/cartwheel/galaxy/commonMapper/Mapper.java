package com.cartwheel.galaxy.commonMapper;

import com.cartwheel.galaxy.dto.MedicineDto;
import com.cartwheel.galaxy.dto.UserDto;
import com.cartwheel.galaxy.entity.Medicine;
import com.cartwheel.galaxy.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public Medicine convertMedicineDtoToMedicine(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        BeanUtils.copyProperties(medicineDto,medicine);
        return medicine;
    }
}
