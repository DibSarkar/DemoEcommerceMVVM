package com.app.nextgrocer.data.model.profile;

public class ProfileRequest {

    private String customer_id;

    public ProfileRequest(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }
}
