package com.app.nextgrocer.data.model.register_login;

public class RegisterLoginResponse {


    /**
     * responseCode : 1
     * responseText : Successfully Login.
     * responseData : {"id":"4","firstname":"payal","lastname":"prachanda","email":"payal1@gmail.com","telephone":"9090909090","newsletter":true}
     */

    private int responseCode;
    private String responseText;
    private ResponseDataBean responseData;

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

    public ResponseDataBean getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataBean responseData) {
        this.responseData = responseData;
    }

    public static class ResponseDataBean {
        /**
         * id : 4
         * firstname : payal
         * lastname : prachanda
         * email : payal1@gmail.com
         * telephone : 9090909090
         * newsletter : true
         */

        private String id;
        private String firstname;
        private String lastname;
        private String email;
        private String telephone;
        private boolean newsletter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public boolean isNewsletter() {
            return newsletter;
        }

        public void setNewsletter(boolean newsletter) {
            this.newsletter = newsletter;
        }
    }
}
