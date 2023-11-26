/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

/**
 *
 * @author PCGarage
 */
public class Supplier {
    private int supplierId = -1;
    private String supplierName;
    private String ContactPerson;
    private String address;
    private String city;
    private String postCode;
    private String country;
    private String phone;

    public Supplier() {
    }

    public Supplier(int supplierId, String supplierName, String ContactPerson, String address, String city, String postCode, String country, String phone) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.ContactPerson = ContactPerson;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.phone = phone;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Supplier{");
        sb.append("supplierId=").append(supplierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", ContactPerson=").append(ContactPerson);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", postCode=").append(postCode);
        sb.append(", country=").append(country);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
    
}
