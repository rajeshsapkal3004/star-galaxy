package com.cartwheel.galaxy.service.impl;

import com.cartwheel.galaxy.entity.ValidationTokenForUser;
import com.cartwheel.galaxy.repository.ValidationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationTokenService {

    @Autowired
    private ValidationTokenRepo validationTokenRepo;

    public ValidationTokenForUser findToken(String token){
        ValidationTokenForUser userToken = validationTokenRepo.findByConfirmationToken(token);
        return userToken;
    }
}
