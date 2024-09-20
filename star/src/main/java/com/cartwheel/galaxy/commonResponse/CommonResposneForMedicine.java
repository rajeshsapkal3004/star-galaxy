package com.cartwheel.galaxy.commonResponse;

public class CommonResposneForMedicine {
    private int statusCode;
    private String message;


    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "CommonResponse [statusCode=" + statusCode + ", message=" + message + "]";
    }

}
