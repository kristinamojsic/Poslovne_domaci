/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetails;
import com.fink.projectpa.data.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class OrderDetailsDao {
    private static final OrderDetailsDao instance = new OrderDetailsDao();
   
   private OrderDetailsDao(){}
   //orderid//productid
   public static OrderDetailsDao getInstance()
   {
       return instance;
   }
   public void create(Connection con, OrderDetails orderDetails) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Integer orderId = null;
            if (orderDetails.getOrder() != null) {
                
                orderId = OrderDao.getInstance().create(con,orderDetails.getOrder());
            }
            ps = con.prepareStatement("INSERT INTO orderDetails(OrderId, ProductId, Quantity) VALUES(?,?,?)");
            
           // Order order = OrderDao.getInstance().find(con, orderDetails.getOrder().getOrderId());
            /*if(order == null)
            {
                throw new Exception("Order " + orderDetails.getOrder() + " doesn't exist in database.");
            }*/
            Product product = ProductDao.getInstance().find(con, orderDetails.getProduct().getProductId());
            if(product == null)
            {
                throw new Exception("Product " + orderDetails.getProduct() + " doesn't exist in database.");
            }
            //ps.setInt(1, orderDetails.getOrderDetailsId());
            ps.setInt(1,orderId);
            ps.setInt(2, orderDetails.getProduct().getProductId());
            ps.setInt(3, orderDetails.getQuantity());
            ps.executeUpdate();
           
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
       
    }
   
   public void update(Connection con, OrderDetails orderDetails) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            //moze da promeni proizvod i kolicinu
            ps = con.prepareStatement("UPDATE orderDetails SET ProductId=?, Quantity=? WHERE OrderDetailsId=?");
          //  ps.setInt(1, orderDetails.getOrder().getOrderId());
            ps.setInt(1, orderDetails.getProduct().getProductId());
            ps.setInt(2, orderDetails.getQuantity());
            ps.setInt(3, orderDetails.getOrderDetailsId());
            
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idOrderDetails) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM orderDetails WHERE OrderDetailsId=?");
            ps.setInt(1, idOrderDetails);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void deleteForOrder(Connection con, int idOrder) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM orderDetails WHERE OrderId=?");
            ps.setInt(1, idOrder);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   public void deleteForProduct(Connection con, int idProduct) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT orderId FROM orderDetails WHERE ProductId=?");
            ps.setInt(1,idProduct);
            rs = ps.executeQuery();
            while(rs.next())
            {
               OrderDao.getInstance().delete(con, rs.getInt(1));
            }
            
            ps = con.prepareStatement("DELETE FROM orderDetails WHERE ProductId=?");
            ps.setInt(1, idProduct);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   
   public OrderDetails find(Connection con, int orderDetailsId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       OrderDetails orderDetails = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM orderDetails WHERE OrderDetailsId=?");
           ps.setInt(1,orderDetailsId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               Order order = OrderDao.getInstance().find(con, rs.getInt("OrderId"));
               Product product = ProductDao.getInstance().find(con, rs.getInt("ProductId"));
                
              
               orderDetails = new OrderDetails(orderDetailsId,order,product,rs.getInt("Quantity"));
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetails;
    }
//proveriti implementaciju
   
   public List<OrderDetails> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<OrderDetails> orderDetailsList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM orderDetails");
           rs = ps.executeQuery();
           while(rs.next())
           {
               Order order = OrderDao.getInstance().find(con, rs.getInt("OrderId"));
               Product product = ProductDao.getInstance().find(con, rs.getInt("ProductId"));
               
               orderDetailsList.add(new OrderDetails(rs.getInt("OrderDetailsId"),order,product,rs.getInt("Quantity")));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return orderDetailsList;
       
       
   } 
}
