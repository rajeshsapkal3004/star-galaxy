package com.cartwheel.galaxy.repository;

import com.cartwheel.galaxy.entity.ValidationTokenForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("validationTokenRepository")
public interface ValidationTokenRepo extends JpaRepository<ValidationTokenForUser, String> {

    ValidationTokenForUser findByConfirmationToken(String confirmationToken);
}
