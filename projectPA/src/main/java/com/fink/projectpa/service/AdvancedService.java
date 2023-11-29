/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.AdvancedDao;
import com.fink.projectpa.dao.CustomerDao;
import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.dao.OrderDetailsDao;
import com.fink.projectpa.dao.ProductDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetails;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PCGarage
 */
public class AdvancedService {
    private static final AdvancedService instance = new AdvancedService();

    public AdvancedService() {
    }
    
    public static AdvancedService getInstance() 
    {
        return instance;
    }
    public String CustomersAndOrders() throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            //return AdvancedDao.getInstance().CustomersAndOrders(con);
            StringBuilder sb = new StringBuilder();
            List<Customer> customers = CustomerDao.getInstance().findAll(con);
            List <Order> orders = OrderDao.getInstance().findAll(con);
            for (Customer customer : customers)
            {
                for(Order order : orders)
                {
                    if(customer.getCustomerID() == order.getCustomer().getCustomerID()){
                        sb.append(customer.getCustomerName());
                        sb.append(" ");
                        sb.append(order.getOrderId());
                        sb.append("\n");
                    }
                }
            }
            return sb.toString();
        }
        catch(SQLException e){
            throw new WarehouseException("There are no customers with orders",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
        
    public List <Product> suppliersProducts(int supplierId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            List <Product> products = ProductDao.getInstance().findAll(con);
            List <Product> productResult = new ArrayList<>();
            //return AdvancedDao.getInstance().suppliersProducts(con,supplierId);
            for(Product product : products)
            {
               if(product.getSupplier().getSupplierId() == supplierId)
               {
                   productResult.add(product);
               }
            }
            return productResult;
        }
        catch(SQLException e){
            throw new WarehouseException("Supplier doesn't have any product",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List <Product> shippersProducts(int shipperId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            //return AdvancedDao.getInstance().shippersProducts(con,shipperId);
            List <Product> result = new ArrayList<>();
            List <OrderDetails> orders = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails order : orders)
            {
                if(order.getOrder()!=null)
                {
                   if(order.getOrder().getShipper().getShipperId()==shipperId)
                    {
                        result.add(order.getProduct());
                    } 
                }
                
            }
            return result;
        }
        catch(SQLException e){
            throw new WarehouseException("Shipper doesn't have any product",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public double totalPriceOrders() throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            //return AdvancedDao.getInstance().totalPriceOrders(con);
            double sum = 0.0;
            List <OrderDetails> orders = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails order : orders)
            {
                sum+=order.getProduct().getPricePerUnit()*order.getQuantity();
            }
            return sum;
        }
        catch(SQLException e){
            throw new WarehouseException("There are no orders in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public double totalPriceForCustomer(int customerId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            //return AdvancedDao.getInstance().totalPriceForCustomer(con, customerId);
            double sum = 0.0;
            List <OrderDetails> orders = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails order : orders)
            {
                if(order.getOrder()!=null)
                {
                  if(order.getOrder().getCustomer().getCustomerID() == customerId)
                {
                    sum+=order.getProduct().getPricePerUnit()*order.getQuantity();    
                }  
                }
                
                
            }
            return sum;
        }
        catch(SQLException e){
            throw new WarehouseException("Given customer doesn't exist in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    public double totalPriceForShipper(int shipperId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            //return AdvancedDao.getInstance().totalPriceForShipper(con,shipperId);
            double sum = 0.0;
            List <OrderDetails> orders = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails order : orders)
            {
                if(order.getOrder()!=null)
                {
                   if(order.getOrder().getShipper().getShipperId() == shipperId)
                {
                    sum+=order.getProduct().getPricePerUnit()*order.getQuantity();    
                } 
                }
                
                
            }
            return sum;
        }
        catch(SQLException e){
            throw new WarehouseException("Given shipper doesn't exist in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public double totalPriceForSupplier(int supplierId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
           // return AdvancedDao.getInstance().totalPriceForSupplier(con,supplierId);
            double sum = 0.0;
            List <OrderDetails> orders = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails order : orders)
            {
                if(order.getProduct().getSupplier().getSupplierId() == supplierId)
                {
                    sum+=order.getProduct().getPricePerUnit()*order.getQuantity();    
                }
                
            }
            return sum;
        }
        catch(SQLException e){
            throw new WarehouseException("Given supplier doesn't exist in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public String MaxOrderPriceEmployee() throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().MaxOrderPriceEmployee(con);
            
            
        }
        catch(Exception e){
            throw new Exception("There are no orders in database ",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public String MostOrdersProducts() throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().MostOrdersProducts(con);
            
            
        }
        catch(Exception e){
            throw new Exception("There are no orders in database ",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public String MaxOrderPriceCustomers() throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
           //return AdvancedDao.getInstance().MaxOrderPriceCustomers(con);
           Map<Double,Customer> mapa = new HashMap<>();
           String result = "";
           List<OrderDetails> orders = OrderDetailsDao.getInstance().findAll(con);
           for(OrderDetails order : orders)
           {
               if(order.getOrder()!=null){
                    mapa.put(totalPriceForCustomer(order.getOrder().getCustomer().getCustomerID()), order.getOrder().getCustomer());    
               }
               
               
           }
           List<Map.Entry<Double, Customer>> lista = new ArrayList<>(mapa.entrySet());
           Collections.sort(lista, Map.Entry.comparingByKey());
           Map<Double, Customer> sortiranaMapa = new HashMap<>();
        
        
           for (Map.Entry<Double, Customer> entry : lista) {
                sortiranaMapa.put(entry.getKey(), entry.getValue());
            }
           int counter = 0;
           for (Map.Entry<Double, Customer> entry : sortiranaMapa.entrySet()) {
            
           result += entry.getKey() + ": " + entry.getValue() + "\n";
           if(counter++==4) break;
                
        }
        return result;  
        }
        catch(SQLException e){
            throw new WarehouseException("There are no orders in database ",e);
        }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public String MaxOrderPriceSupplier() throws Exception
    {
       Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().MaxOrderPriceSupplier(con);
            
        }
        catch(Exception e){
            throw new Exception("There are no orders in database ",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        } 
    }
}
