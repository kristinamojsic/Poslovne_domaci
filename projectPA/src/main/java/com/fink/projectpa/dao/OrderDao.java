/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.Shipper;
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
public class OrderDao {
    private static final OrderDao instance = new OrderDao();
   
   private OrderDao(){}
   
   public static OrderDao getInstance()
   {
       return instance;
   }
   public int create(Connection con, Order order) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO orders(OrderDate, CustomerId, EmployeeId, ShipperId) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            Customer customer = CustomerDao.getInstance().find(con, order.getCustomer().getCustomerID());
            if(customer == null)
            {
                throw new Exception("Customer " + order.getCustomer() + " doesn't exist in database.");
            }
            Employee employee = EmployeeDao.getInstance().find(con, order.getEmployee().getEmployeeId());
            if(employee == null)
            {
                throw new Exception("Employee " + order.getEmployee() + " doesn't exist in database.");
            }
            Shipper shipper = ShipperDao.getInstance().find(con, order.getShipper().getShipperId());
            if(shipper == null)
            {
                throw new Exception("Shipper " + order.getShipper().getShipperId() + " doesn't exist in database.");
            }
            ps.setString(1, order.getOrderDate());
            ps.setInt(2,order.getCustomer().getCustomerID());
            ps.setInt(3, order.getEmployee().getEmployeeId());
            ps.setInt(4, order.getShipper().getShipperId());
           
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
       
    }
   
   public void update(Connection con, Order order) throws Exception
   {
       //treba proveriti da li postoje
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE orders SET OrderDate=?, CustomerId=?, EmployeeId=?, ShipperId=? WHERE OrderId=?");
            Customer customer = CustomerDao.getInstance().find(con, order.getCustomer().getCustomerID());
            if(customer == null)
            {
                throw new Exception("Customer " + order.getCustomer() + " doesn't exist in database.");
            }
            Employee employee = EmployeeDao.getInstance().find(con, order.getEmployee().getEmployeeId());
            if(employee == null)
            {
                throw new Exception("Employee " + order.getEmployee() + " doesn't exist in database.");
            }
            Shipper shipper = ShipperDao.getInstance().find(con, order.getShipper().getShipperId());
            if(shipper == null)
            {
                throw new Exception("Shipper " + order.getShipper().getShipperId() + " doesn't exist in database.");
            }
            ps.setString(1, order.getOrderDate());
            ps.setInt(2, order.getCustomer().getCustomerID());
            ps.setInt(3, order.getEmployee().getEmployeeId());
            ps.setInt(4, order.getShipper().getShipperId());
            ps.setInt(5, order.getOrderId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idOrder) throws SQLException
   {
       PreparedStatement ps = null;
       //PreparedStatement ps2 = null;
        try {
            
            ps = con.prepareStatement("DELETE FROM orders WHERE OrderId=?");
            ps.setInt(1, idOrder);
            ps.executeUpdate();
            
        } finally {
            ResourcesManager.closeResources(null, ps);
            
        }
   }
   public void deleteForEmployee(Connection con, int EmployeeId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       
        try {
            ps = con.prepareStatement("SELECT orderId FROM orders WHERE EmployeeId=?");
            ps.setInt(1,EmployeeId);
            rs = ps.executeQuery();
            while(rs.next())
            {
               OrderDetailsDao.getInstance().deleteForOrder(con, rs.getInt(1));
               
            }
            ps = con.prepareStatement("DELETE FROM orders WHERE EmployeeId=?");
            ps.setInt(1, EmployeeId);
            ps.executeUpdate();
            
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
      public void deleteForShipper(Connection con, int ShipperId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       
        try {
            ps = con.prepareStatement("SELECT orderId FROM orders WHERE ShipperId=?");
            ps.setInt(1,ShipperId);
            rs = ps.executeQuery();
            while(rs.next())
            {
               OrderDetailsDao.getInstance().deleteForOrder(con, rs.getInt(1));
               
            }
            ps = con.prepareStatement("DELETE FROM orders WHERE ShipperId=?");
            ps.setInt(1, ShipperId);
            ps.executeUpdate();
            
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
      public void deleteForCustomer(Connection con, int CustomerId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       
        try {
            ps = con.prepareStatement("SELECT orderId FROM orders WHERE CustomerId=?");
            ps.setInt(1,CustomerId);
            rs = ps.executeQuery();
            while(rs.next())
            {
               OrderDetailsDao.getInstance().deleteForOrder(con, rs.getInt(1));
               
            }
            ps = con.prepareStatement("DELETE FROM orders WHERE CustomerId=?");
            ps.setInt(1, CustomerId);
            ps.executeUpdate();
            
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   /*public List<Integer> findOrdersWithProductId(Connection con, int productId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Integer> orders = new ArrayList<>();
       try
       {
           ps = con.prepareStatement("SELECT orderId FROM orderDetails WHERE ProductId=?");
           ps.setInt(1,productId);
           rs = ps.executeQuery();
           while(rs.next())
           {
               orders.add(rs.getInt(1));
           }
       }
       finally {
            ResourcesManager.closeResources(rs, ps);
        }
       return orders;
   }*/
   public Order find(Connection con, int orderId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       Order order = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM orders WHERE OrderId=?");
           ps.setInt(1,orderId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               Customer customer = CustomerDao.getInstance().find(con, rs.getInt("CustomerId"));
               Employee employee = EmployeeDao.getInstance().find(con, rs.getInt("EmployeeId"));
               Shipper shipper = ShipperDao.getInstance().find(con, rs.getInt("ShipperId")); 
              
               order = new Order(orderId,rs.getString("OrderDate"),customer,employee,shipper);
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return order;
    }
//proveriti implementaciju
   
   public List<Order> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Order> orderList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM orders");
           rs = ps.executeQuery();
           while(rs.next())
           {
               Customer customer = CustomerDao.getInstance().find(con, rs.getInt("CustomerId"));
               Employee employee = EmployeeDao.getInstance().find(con, rs.getInt("EmployeeId"));
               Shipper shipper = ShipperDao.getInstance().find(con, rs.getInt("ShipperId")); 
               orderList.add(new Order(rs.getInt("OrderId"),rs.getString("OrderDate"),customer,employee,shipper));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return orderList;
       
       
   }    
}
