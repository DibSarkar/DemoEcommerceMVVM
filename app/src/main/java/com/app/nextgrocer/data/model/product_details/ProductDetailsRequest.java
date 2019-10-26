package com.app.nextgrocer.data.model.product_details;


public class ProductDetailsRequest {

    private String product_id;

    public ProductDetailsRequest(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_id() {
        return product_id;
    }
}
