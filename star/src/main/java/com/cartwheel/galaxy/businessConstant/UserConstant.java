package com.cartwheel.galaxy.businessConstant;

import org.springframework.stereotype.Component;

@Component
public class UserConstant {

    public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String[] ADMIN_ACCESS = { "ROLE_ADMIN", "ROLE_MODERATOR" };
    public static final String[] MODERATOR_ACCESS = { "ROLE_MODERATOR" };

}
