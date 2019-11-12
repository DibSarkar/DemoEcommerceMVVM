package com.app.nextgrocer.data.model.profile;

public class EditProfileRequest {

    private String customer_id;
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String newsletter;



    public EditProfileRequest(String customer_id,String firstname, String lastname, String email, String telephone, String newsletter) {

        this.customer_id = customer_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
        this.newsletter = newsletter;
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


    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

}


