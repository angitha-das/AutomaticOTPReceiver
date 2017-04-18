package com.example.angithadas.otpverification;

/**
 * Created by angitha on 18/4/17.
 */

public class OTPEventBus {

    private final String message;

    public OTPEventBus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
