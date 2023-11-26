/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Supplier;
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
public class SupplierDao {
    private static final SupplierDao instance = new SupplierDao();
   
   private SupplierDao(){}
   
   public static SupplierDao getInstance()
   {
       return instance;
   }
   public void create(Connection con, Supplier supplier) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO suppliers(SupplierName, ContactPerson, Address, City, PostCode, Country, Phone) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setString(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.executeUpdate();
            //rs = ps.getGeneratedKeys();
            //rs.next();
            //id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
       // return id;
    }
   
   public void update(Connection con, Supplier supplier) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE suppliers SET SupplierName=?, ContactPerson=?, Address=?, City=?, PostCode=?, Country=? WHERE SupplierId=?");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setString(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.setInt(7, supplier.getSupplierId());
            
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idSupplier) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM suppliers WHERE SupplierId=?");
            //dobiti informaciju da li je obrisao?
            ps.setInt(1, idSupplier);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   
   public Supplier find(Connection con, int supplierId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       Supplier supplier = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM suppliers WHERE SupplierId=?");
           ps.setInt(1,supplierId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               supplier = new Supplier(rs.getInt("SupplierId"),rs.getString("SupplierName"),rs.getString("ContactPerson"),rs.getString("Address"),rs.getString("City"),rs.getString("PostCode"),rs.getString("Country"),rs.getString("Phone"));
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
//proveriti implementaciju
   
   public List<Supplier> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Supplier> supplierList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM suppliers");
           rs = ps.executeQuery();
           while(rs.next())
           {
               supplierList.add(new Supplier(rs.getInt("SupplierId"),rs.getString("SupplierName"),rs.getString("ContactPerson"),rs.getString("Address"),rs.getString("City"),rs.getString("PostCode"),rs.getString("Country"),rs.getString("Phone")));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return supplierList;
       
       
   }
}
