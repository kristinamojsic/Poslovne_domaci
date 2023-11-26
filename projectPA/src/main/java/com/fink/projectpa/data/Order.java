/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

/**
 *
 * @author PCGarage
 */
public class Order {
    private int orderId;
    private String orderDate;
    private Customer customer;
    private Employee employee;
    private Shipper shipper;

    public Order() {
    }

    public Order(int orderId, String orderDate, Customer customer, Employee employee, Shipper shipper) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }
    

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", customer=" + customer + ", employee=" + employee + ", shipper=" + shipper + '}';
    }
}
