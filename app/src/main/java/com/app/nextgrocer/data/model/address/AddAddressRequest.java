package com.app.nextgrocer.data.model.address;

public class AddAddressRequest {

    private String customer_id;
    private String firstname;
    private String lastname;
    private String address_1;
    private String address_2;
    private String postcode;
    private String state_id;
    private String country_id;
    private String default_address;
    private String company;
    private String city;


    public AddAddressRequest(String customer_id, String firstname, String lastname, String address_1, String address_2, String postcode, String state_id, String country_id, String default_address, String company, String city) {
        this.customer_id = customer_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.postcode = postcode;
        this.state_id = state_id;
        this.country_id = country_id;
        this.default_address = default_address;
        this.company = company;
        this.city = city;
    }


    public String getCustomer_id() {
        return customer_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress_1() {
        return address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getState_id() {
        return state_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getDefault_address() {
        return default_address;
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }
}
