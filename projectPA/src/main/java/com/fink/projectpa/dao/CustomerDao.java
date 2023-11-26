/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class CustomerDao {
    private static final CustomerDao instance = new CustomerDao();
   
   private CustomerDao(){}
   
   public static CustomerDao getInstance()
   {
       return instance;
   }
   public void create(Connection con, Customer customer) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO customers(CustomerName, ContactPerson, Address, City, PostCode, Country) VALUES(?,?,?,?,?,?)");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();
            //rs = ps.getGeneratedKeys();
            //rs.next();
            //id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
       // return id;
    }
   
   public void update(Connection con, Customer customer) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE customers SET CustomerName=?, ContactPerson=?, Address=?, City=?, PostCode=?, Country=? WHERE CustomerId=?");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getContactPerson());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, customer.getCustomerID());
            
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idCustomer) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM customers WHERE CustomerId=?");
            //dobiti informaciju da li je obrisao?
            ps.setInt(1, idCustomer);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   
   public Customer find(Connection con, int customerId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       Customer customer = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM customers WHERE CustomerId=?");
           ps.setInt(1,customerId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               customer = new Customer(customerId,rs.getString("CustomerName"),rs.getString("ContactPerson"),rs.getString("Address"),rs.getString("City"),rs.getString("PostCode"),rs.getString("Country"));
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }
//proveriti implementaciju
   
   public List<Customer> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Customer> customerList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM customers");
           rs = ps.executeQuery();
           while(rs.next())
           {
               customerList.add(new Customer(rs.getInt("CustomerId"),rs.getString("CustomerName"),rs.getString("ContactPerson"),rs.getString("Address"),rs.getString("City"),rs.getString("PostCode"),rs.getString("Country")));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return customerList;
       
       
   }
}
