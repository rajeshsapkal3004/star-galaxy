package com.cartwheel.galaxy.service.impl;

import com.cartwheel.galaxy.entity.ValidationTokenForUser;
import com.cartwheel.galaxy.repository.ValidationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationToken {

    @Autowired
    private ValidationTokenRepo validationTokenRepo;

    public ValidationTokenForUser createToken(ValidationTokenForUser validationTokenForUser){
        ValidationTokenForUser token = null;
        if(validationTokenForUser!= null){
            token=this.validationTokenRepo.save(validationTokenForUser);
        }
        return token;
    }
}
