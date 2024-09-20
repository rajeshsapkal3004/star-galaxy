package com.cartwheel.galaxy.dto;

public class JwtResponseDto {
    private String token;

    public JwtResponseDto() {

    }

    public JwtResponseDto(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
