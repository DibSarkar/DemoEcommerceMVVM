package com.app.nextgrocer.data.model.local_models;

public class ProfileDataResponse {

    private String name;
    private String emaild;
    private String mobile;

    public ProfileDataResponse(String name, String emaild, String mobile) {
        this.name = name;
        this.emaild = emaild;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmaild() {
        return emaild;
    }

    public void setEmaild(String emaild) {
        this.emaild = emaild;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
