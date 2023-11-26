/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Shipper;
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
public class AdvancedDao {
    private static final AdvancedDao instance = new AdvancedDao();
   
   private AdvancedDao(){}
   
   public static AdvancedDao getInstance()
   {
       return instance;
   }
   public String CustomersAndOrders(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       String result="";
       try
       {
           ps = con.prepareStatement("SELECT c.CustomerName, o.OrderId FROM Customers c JOIN Orders o ON c.CustomerId = o.CustomerId ORDER BY c.CustomerName, o.OrderId");
           
           rs = ps.executeQuery();
           while(rs.next())
           {
               result += rs.getString("CustomerName") + " " + rs.getInt("OrderId") + "\n";
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return result;
    } 
   
   public List <Product> suppliersProducts(Connection con, int supplierId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List <Product> products = new ArrayList<>();
       try
       {
           ps = con.prepareStatement("SELECT * FROM products WHERE SupplierId = ?");
           ps.setInt(1, supplierId);
           rs = ps.executeQuery();
           
           while(rs.next())
           {
               products.add(new Product(rs.getInt("ProductId"),rs.getString("ProductName"),SupplierDao.getInstance().find(con, supplierId),rs.getString("ProductCategory"),rs.getDouble("PricePerUnit")));
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return products;
   }
   
   public List <Product> shippersProducts(Connection con, int shipperId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List <Product> products = new ArrayList<>();
       try
       {
           ps = con.prepareStatement("SELECT p.ProductId, p.ProductName, p.SupplierId, p.ProductCategory, p.PricePerUnit from orders o INNER JOIN orderdetails od on o.OrderId = od.OrderId INNER JOIN products p ON od.ProductId=p.ProductId WHERE o.ShipperId = ?");
           ps.setInt(1, shipperId);
           rs = ps.executeQuery();
           
           while(rs.next())
           {
               Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("SupplierId"));
               products.add(new Product(rs.getInt("ProductId"),rs.getString("ProductName"),supplier,rs.getString("ProductCategory"),rs.getDouble("PricePerUnit")));
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return products;
   }
   
   public double totalPriceOrders(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       double totalPrice = 0;
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit*Quantity) as totalPrice FROM orderdetails od INNER JOIN products p ON od.ProductId = p.ProductId");
           rs = ps.executeQuery();
           if(rs.next())
           {
               totalPrice = rs.getDouble("totalPrice");
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return totalPrice;
   }
   
   /*SELECT SUM(PricePerUnit*Quantity) as totalPrice FROM orderdetails od INNER JOIN orders o ON od.OrderId = o.OrderId INNER JOIN products p ON od.ProductId = p.ProductId WHERE o.CustomerId = 1;*/
   public double totalPriceForCustomer(Connection con, int customerId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       double totalPrice = 0;
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit*Quantity) as totalPrice FROM orderdetails od INNER JOIN orders o ON od.OrderId = o.OrderId INNER JOIN products p ON od.ProductId = p.ProductId WHERE o.CustomerId = ?");
           ps.setInt(1, customerId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               totalPrice = rs.getDouble("totalPrice");
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return totalPrice;
   }
   
   public double totalPriceForShipper(Connection con, int shipperId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       double totalPrice = 0;
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit*Quantity) as totalPrice FROM orderdetails od INNER JOIN orders o ON od.OrderId = o.OrderId INNER JOIN products p ON od.ProductId = p.ProductId WHERE o.ShipperId = ?");
           ps.setInt(1, shipperId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               totalPrice = rs.getDouble("totalPrice");
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return totalPrice;
   }
   
   public double totalPriceForSupplier(Connection con, int supplierId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       double totalPrice = 0;
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit*Quantity) as totalPrice FROM orderdetails od INNER JOIN products p ON od.ProductId = p.ProductId WHERE p.SupplierId = ?");
           ps.setInt(1, supplierId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               totalPrice = rs.getDouble("totalPrice");
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return totalPrice;
   }
   
   public String MaxOrderPriceEmployee(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       String result = "";
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit * Quantity) as totalPrice, e.FirstName, e.LastName FROM orders o INNER JOIN orderdetails od ON o.OrderId = od.OrderId INNER JOIN employees e ON o.EmployeeId = e.EmployeeId INNER JOIN products p ON od.ProductId = p.ProductId GROUP BY o.EmployeeId order by totalPrice desc LIMIT 1");
           rs = ps.executeQuery();
           result = rs.getString("totalPrice") + rs.getString("FirstName") + rs.getString("LastName");
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
           
       }
       return result;
   }
   
   public String MostOrdersProducts(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       String result = "";
       try
       {
           ps = con.prepareStatement("SELECT p.ProductName,COUNT(od.OrderId) as orderCount FROM orderdetails od INNER JOIN products p ON od.ProductId = p.ProductId GROUP BY p.ProductId ORDER BY orderCount DESC LIMIT 2");
           rs = ps.executeQuery();
           while(rs.next())
           {
               result += "Order count:" + rs.getString("orderCount")+ ", product " + rs.getString("ProductName") + "\n";
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return result;
   }
   
   public String MaxOrderPriceCustomers(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       String result = "";
       //List<Customer> customers = new ArrayList<>();
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit * Quantity) as totalPrice, c.CustomerId, c.CustomerName, c.ContactPerson, c.Address, c.City,c.PostCode,c.Country FROM orders o INNER JOIN orderdetails od ON o.OrderId = od.OrderId INNER JOIN customers c ON o.CustomerId = c.CustomerId INNER JOIN products p ON od.ProductId = p.ProductId GROUP BY o.CustomerId order by totalPrice desc LIMIT 4");
           rs = ps.executeQuery();
           
           while(rs.next())
           {
               Customer customer = new Customer(rs.getInt("CustomerId"),rs.getString("CustomerName"),rs.getString("ContactPerson"),rs.getString("Address"),rs.getString("City"),rs.getString("PostCode"),rs.getString("Country"));
               result += rs.getDouble("totalPrice") + customer.toString();
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return result;
   }
   /*SELECT SUM(PricePerUnit * Quantity) as totalPrice, s.SupplierId, s.SupplierName,s.ContactPerson,s.Address,s.City,s.PostCode,s.Country,s.Phone FROM orders o INNER JOIN orderdetails od ON o.OrderId = od.OrderId INNER JOIN products p ON od.ProductId = p.ProductId INNER JOIN suppliers s ON p.SupplierId = s.SupplierId GROUP BY p.SupplierId order by totalPrice desc LIMIT 1;*/
   public String MaxOrderPriceSupplier(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       String result = "";
       
       try
       {
           ps = con.prepareStatement("SELECT SUM(PricePerUnit * Quantity) as totalPrice, s.SupplierId, s.SupplierName,s.ContactPerson,s.Address,s.City,s.PostCode,s.Country,s.Phone FROM orders o INNER JOIN orderdetails od ON o.OrderId = od.OrderId INNER JOIN products p ON od.ProductId = p.ProductId INNER JOIN suppliers s ON p.SupplierId = s.SupplierId GROUP BY p.SupplierId order by totalPrice desc LIMIT 1");
           rs = ps.executeQuery();
           
           while(rs.next())
           {
                 
               Supplier supplier = new Supplier(rs.getInt("SupplierId"),rs.getString("SupplierName"),rs.getString("ContactPerson"),rs.getString("Address"),rs.getString("City"),rs.getString("PostCode"),rs.getString("Country"),rs.getString("Phone"));
              
               result += rs.getDouble("totalPrice") + " " + supplier.toString();
           }
       }
       finally
       {
           ResourcesManager.closeResources(rs, ps);
       }
       return result;
   }
   
}
