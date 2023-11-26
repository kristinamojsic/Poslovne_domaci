/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

/**
 *
 * @author PCGarage
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String contactPerson;
    private String address;
    private String city;
    private String postCode;
    private String country;

    public Customer() {
    }

    public Customer(int customerID, String customerName, String contactPerson, String address, String city, String postCode, String country) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customers{");
        sb.append("customerID=").append(customerID);
        sb.append(", customerName=").append(customerName);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", postCode=").append(postCode);
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }
}
