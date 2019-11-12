package com.app.nextgrocer.data.model.product_list;

import java.util.List;

public class ProductListResponse {


    /**
     * responseCode : 1
     * responseText : Successfully Data Found.
     * feature : [{"product_id":"42","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img6-300x200.png","name":"Lorem ipsum dolor sit amet, consectetur","price":"122","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=42"},{"product_id":"50","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img2-300x200.png","name":"2 Lorem ipsum dolor sit amet, consectetur","price":"242","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=50"},{"product_id":"51","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img1-300x200.png","name":"3 Lorem ipsum dolor sit amet, consectetur","price":"362","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=51"},{"product_id":"52","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img10-300x200.png","name":"4 Lorem ipsum dolor sit amet, consectetur","price":"482","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=52"},{"product_id":"59","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img7-300x200.png","name":"11 Lorem ipsum dolor sit amet, consectetur","price":"1322","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=59"},{"product_id":"58","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img9-300x200.png","name":"10 Lorem ipsum dolor sit amet, consectetur","price":"1202","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=58"},{"product_id":"53","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img11-300x200.png","name":"5 Lorem ipsum dolor sit amet, consectetur","price":"602","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=53"},{"product_id":"54","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img3-300x200.png","name":"6 Lorem ipsum dolor sit amet, consectetur","price":"722","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=54"},{"product_id":"55","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img4-300x200.png","name":"7 Lorem ipsum dolor sit amet, consectetur","price":"842","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=55"},{"product_id":"56","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img5-300x200.png","name":"8 Lorem ipsum dolor sit amet, consectetur","price":"962","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=56"},{"product_id":"57","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img8-300x200.png","name":"9 Lorem ipsum dolor sit amet, consectetur","price":"1082","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=57"}]
     * newarrival : [{"product_id":"59","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img7-300x200.png","name":"11 Lorem ipsum dolor sit amet, consectetur","price":"1322","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=59"},{"product_id":"58","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img9-300x200.png","name":"10 Lorem ipsum dolor sit amet, consectetur","price":"1202","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=58"},{"product_id":"57","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img8-300x200.png","name":"9 Lorem ipsum dolor sit amet, consectetur","price":"1082","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=57"},{"product_id":"56","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img5-300x200.png","name":"8 Lorem ipsum dolor sit amet, consectetur","price":"962","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=56"},{"product_id":"55","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img4-300x200.png","name":"7 Lorem ipsum dolor sit amet, consectetur","price":"842","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=55"},{"product_id":"54","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img3-300x200.png","name":"6 Lorem ipsum dolor sit amet, consectetur","price":"722","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=54"},{"product_id":"53","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img11-300x200.png","name":"5 Lorem ipsum dolor sit amet, consectetur","price":"602","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=53"},{"product_id":"52","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img10-300x200.png","name":"4 Lorem ipsum dolor sit amet, consectetur","price":"482","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=52"},{"product_id":"51","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img1-300x200.png","name":"3 Lorem ipsum dolor sit amet, consectetur","price":"362","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=51"},{"product_id":"50","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img2-300x200.png","name":"2 Lorem ipsum dolor sit amet, consectetur","price":"242","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=50"},{"product_id":"42","thumb":"http://192.168.5.51/dheman/image/cache/catalog/img6-300x200.png","name":"Lorem ipsum dolor sit amet, consectetur","price":"122","special":"","wishlist":"0","rating":0,"href":"http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=42"}]
     */

    private int responseCode;
    private String responseText;
    private List<FeatureBean> feature;
    private List<NewarrivalBean> newarrival;

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

    public List<FeatureBean> getFeature() {
        return feature;
    }

    public void setFeature(List<FeatureBean> feature) {
        this.feature = feature;
    }

    public List<NewarrivalBean> getNewarrival() {
        return newarrival;
    }

    public void setNewarrival(List<NewarrivalBean> newarrival) {
        this.newarrival = newarrival;
    }

    public static class FeatureBean {
        /**
         * product_id : 42
         * thumb : http://192.168.5.51/dheman/image/cache/catalog/img6-300x200.png
         * name : Lorem ipsum dolor sit amet, consectetur
         * price : 122
         * special :
         * wishlist : 0
         * rating : 0
         * href : http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=42
         */

        private String product_id;
        private String thumb;
        private String name;
        private String price;
        private String special;
        private String wishlist;
        private int rating;
        private String href;

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

        public String getWishlist() {
            return wishlist;
        }

        public void setWishlist(String wishlist) {
            this.wishlist = wishlist;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class NewarrivalBean {
        /**
         * product_id : 59
         * thumb : http://192.168.5.51/dheman/image/cache/catalog/img7-300x200.png
         * name : 11 Lorem ipsum dolor sit amet, consectetur
         * price : 1322
         * special :
         * wishlist : 0
         * rating : 0
         * href : http://192.168.5.51/dheman/index.php?route=product/product&amp;product_id=59
         */

        private String product_id;
        private String thumb;
        private String name;
        private String price;
        private String special;
        private String wishlist;
        private int rating;
        private String href;

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

        public String getWishlist() {
            return wishlist;
        }

        public void setWishlist(String wishlist) {
            this.wishlist = wishlist;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
