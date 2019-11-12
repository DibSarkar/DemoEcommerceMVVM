package com.app.nextgrocer.data.model.product_list;

public class CategoryProductRequest {

    private String category_id;

    public CategoryProductRequest(String category_id) {
        this.category_id = category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_id() {
        return category_id;
    }
}
