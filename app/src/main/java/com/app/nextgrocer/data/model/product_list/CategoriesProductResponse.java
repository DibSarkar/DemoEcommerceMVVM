package com.app.nextgrocer.data.model.product_list;

import java.util.List;

public class CategoriesProductResponse {


    /**
     * responseCode : 1
     * responseText : Successfully Data Found.
     * product : [{"product_id":"42","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img6-300x200.png","name":"Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"122.00","special":"0.00","tax":"100.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"57","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img8-300x200.png","name":"9 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"1082.00","special":"0.00","tax":"900.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"56","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img5-300x200.png","name":"8 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"962.00","special":"0.00","tax":"800.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"55","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img4-300x200.png","name":"7 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"842.00","special":"0.00","tax":"700.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"54","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img3-300x200.png","name":"6 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"722.00","special":"0.00","tax":"600.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"53","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img11-300x200.png","name":"5 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"602.00","special":"0.00","tax":"500.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"52","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img10-300x200.png","name":"4 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"482.00","special":"0.00","tax":"400.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"51","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img1-300x200.png","name":"3 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"362.00","special":"0.00","tax":"300.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"50","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img2-300x200.png","name":"2 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"242.00","special":"0.00","tax":"200.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"59","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img7-300x200.png","name":"11 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"1322.00","special":"0.00","tax":"1100.0000","stock":"In Stock","minimum":"2","rating":0},{"product_id":"58","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img9-300x200.png","name":"10 Lorem ipsum dolor sit amet, consectetur","description":"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..","price":"1202.00","special":"0.00","tax":"1000.0000","stock":"In Stock","minimum":"2","rating":0}]
     */

    private int responseCode;
    private String responseText;
    private List<ProductBean> product;

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

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
        /**
         * product_id : 42
         * thumb : http://192.168.5.51/dheman/image/cache/catalog/img6-300x200.png
         * name : Lorem ipsum dolor sit amet, consectetur
         * description : The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution. Designed speci..
         * price : 122.00
         * special : 0.00
         * tax : 100.0000
         * stock : In Stock
         * minimum : 2
         * rating : 0
         */

        private String product_id;
        private String thumb;
        private String name;
        private String description;
        private String price;
        private String special;
        private String tax;
        private String stock;
        private String minimum;
        private int rating;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
    }
}
