package com.app.nextgrocer.data.model.register_login;

public class LoginRequest {

    private String email;
    private String password;
    private String device_type;
    private String device_token;


    public LoginRequest(String email, String password, String device_type, String device_token) {
        this.email = email;
        this.password = password;
        this.device_type = device_type;
        this.device_token = device_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
