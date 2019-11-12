package com.app.nextgrocer.data.model.categories;

import java.util.List;

public class CategoriesResponse {


    /**
     * responseCode : 1
     * responseText : Successfully Data Found.
     * data : [{"id":"61","name":"Dairy & Eggs","image":"http://192.168.5.51/dheman/image/cache/catalog/ico1-48x47.png","childCategory":[]},{"id":"59","name":"Fruits & Vegetable","image":"http://192.168.5.51/dheman/image/cache/catalog/ico2-48x47.png","childCategory":[]},{"id":"62","name":"Meat & Fish","image":"http://192.168.5.51/dheman/image/cache/catalog/ico3-48x47.png","childCategory":[]},{"id":"60","name":"Oil & Spices","image":"http://192.168.5.51/dheman/image/cache/catalog/ico4-48x47.png","childCategory":[]},{"id":"63","name":"Package Food","image":"http://192.168.5.51/dheman/image/cache/catalog/ico5-48x47.png","childCategory":[]}]
     */

    private int responseCode;
    private String responseText;
    private List<DataBean> data;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 61
         * name : Dairy & Eggs
         * image : http://192.168.5.51/dheman/image/cache/catalog/ico1-48x47.png
         * childCategory : []
         */

        private String id;
        private String name;
        private String image;
        private List<?> childCategory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<?> getChildCategory() {
            return childCategory;
        }

        public void setChildCategory(List<?> childCategory) {
            this.childCategory = childCategory;
        }
    }
}
