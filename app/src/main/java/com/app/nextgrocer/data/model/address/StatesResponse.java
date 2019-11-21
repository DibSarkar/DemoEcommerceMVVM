package com.app.nextgrocer.data.model.address;

import java.util.List;

public class StatesResponse {


    /**
     * responseCode : 1
     * responseText : Successfully Data Found.
     * data : [{"id":"1475","name":"Andaman and Nicobar Islands"},{"id":"1476","name":"Andhra Pradesh"},{"id":"1477","name":"Arunachal Pradesh"},{"id":"1478","name":"Assam"},{"id":"1479","name":"Bihar"},{"id":"1480","name":"Chandigarh"},{"id":"1481","name":"Dadra and Nagar Haveli"},{"id":"1482","name":"Daman and Diu"},{"id":"1483","name":"Delhi"},{"id":"1484","name":"Goa"},{"id":"1485","name":"Gujarat"},{"id":"1486","name":"Haryana"},{"id":"1487","name":"Himachal Pradesh"},{"id":"1488","name":"Jammu and Kashmir"},{"id":"1489","name":"Karnataka"},{"id":"1490","name":"Kerala"},{"id":"1491","name":"Lakshadweep Islands"},{"id":"1492","name":"Madhya Pradesh"},{"id":"1493","name":"Maharashtra"},{"id":"1494","name":"Manipur"},{"id":"1495","name":"Meghalaya"},{"id":"1496","name":"Mizoram"},{"id":"1497","name":"Nagaland"},{"id":"1498","name":"Orissa"},{"id":"1499","name":"Puducherry"},{"id":"1500","name":"Punjab"},{"id":"1501","name":"Rajasthan"},{"id":"1502","name":"Sikkim"},{"id":"1503","name":"Tamil Nadu"},{"id":"1504","name":"Tripura"},{"id":"1505","name":"Uttar Pradesh"},{"id":"1506","name":"West Bengal"},{"id":"4231","name":"Telangana"}]
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
         * id : 1475
         * name : Andaman and Nicobar Islands
         */

        private String id;
        private String name;

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
    }
}
