/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Product;
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
public class ProductDao {
   private static final ProductDao instance = new ProductDao();
   
   private ProductDao(){}
   
   public static ProductDao getInstance()
   {
       return instance;
   }
   public void create(Connection con, Product product) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO products(ProductName, SupplierId, ProductCategory, PricePerUnit) VALUES(?,?,?,?)");
            ps.setString(1, product.getProductName());
            Supplier supplier = SupplierDao.getInstance().find(con,product.getSupplier().getSupplierId());
            if (supplier == null) {
                throw new Exception("Supplier " + product.getSupplier() + " doesn't exist in database.");
            }
            ps.setInt(2,product.getSupplier().getSupplierId());
            ps.setString(3, product.getProductCategory());
            ps.setDouble(4, product.getPricePerUnit());
           
            ps.executeUpdate();
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
       
    }
   
   public void update(Connection con, Product product) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE products SET ProductName=?, SupplierId=?, ProductCategory=?, PricePerUnit=? WHERE ProductId=?");
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getSupplier().getSupplierId());
            ps.setString(3, product.getProductCategory());
            ps.setDouble(4, product.getPricePerUnit());
            ps.setInt(5, product.getProductId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idProduct) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM products WHERE ProductId=?");
            ps.setInt(1, idProduct);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   
   public Product find(Connection con, int productId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       Product product = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM products WHERE ProductId=?");
           ps.setInt(1,productId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("SupplierId"));
               product = new Product(productId,rs.getString("ProductName"),supplier,rs.getString("ProductCategory"),rs.getDouble("PricePerUnit"));
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }
//proveriti implementaciju
   
   public List<Product> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Product> productList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM products");
           rs = ps.executeQuery();
           while(rs.next())
           {
               Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("SupplierId"));
               productList.add(new Product(rs.getInt("ProductId"),rs.getString("ProductName"),supplier,rs.getString("ProductCategory"),rs.getDouble("PricePerUnit")));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return productList;
       
       
   }     
}
