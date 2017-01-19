package uk.me.paulriley.typicodeclient.services.model;

import java.io.Serializable;

public class UserResultsModel implements Serializable {
    private int id;
    private String name;
    private String username;
    private String email;
    private UserAddressModel address;
    private String phone;
    private String website;
    private UserCompanyModel company;

    public UserResultsModel(int id, String name, String userName, String email
        , UserAddressModel address, String phone, String webSite, UserCompanyModel company) {
        this.id = id;
        this.name = name;
        this.username = userName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = webSite;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAddressModel getAddress() {
        return address;
    }

    public void setAddress(UserAddressModel address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public UserCompanyModel getCompany() {
        return company;
    }

    public void setCompany(UserCompanyModel company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
