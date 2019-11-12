package com.app.nextgrocer.data.model.changePass;

public class ChangePassRequest {

    private String customer_id;
    private String password;

    public ChangePassRequest(String customer_id, String password) {
        this.customer_id = customer_id;
        this.password = password;
    }


    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
