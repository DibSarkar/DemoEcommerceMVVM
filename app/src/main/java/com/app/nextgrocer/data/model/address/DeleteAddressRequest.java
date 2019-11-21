package com.app.nextgrocer.data.model.address;

public class DeleteAddressRequest {

    private String address_id;
    private String customer_id;

    public DeleteAddressRequest(String customer_id, String address_id) {
        this.address_id = address_id;
        this.customer_id = customer_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }
}
