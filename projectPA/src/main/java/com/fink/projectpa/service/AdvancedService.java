/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.AdvancedDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.data.Supplier;
import java.sql.Connection;
import java.util.List;

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
    public String CustomersAndOrders() throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().CustomersAndOrders(con);
        }
        catch(Exception e){
            throw new Exception("There are no customers with orders",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
        
    public List <Product> suppliersProducts(int supplierId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().suppliersProducts(con,supplierId);
        }
        catch(Exception e){
            throw new Exception("Supplier doesn't have any product",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List <Product> shippersProducts(int shipperId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().shippersProducts(con,shipperId);
        }
        catch(Exception e){
            throw new Exception("Shipper doesn't have any product",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public double totalPriceOrders() throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().totalPriceOrders(con);
        }
        catch(Exception e){
            throw new Exception("There are no orders in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public double totalPriceForCustomer(int customerId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().totalPriceForCustomer(con, customerId);
        }
        catch(Exception e){
            throw new Exception("Given customer doesn't exist in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    public double totalPriceForShipper(int shipperId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().totalPriceForShipper(con,shipperId);
        }
        catch(Exception e){
            throw new Exception("Given shipper doesn't exist in database",e);
    }
        finally
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public double totalPriceForSupplier(int supplierId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().totalPriceForSupplier(con,supplierId);
        }
        catch(Exception e){
            throw new Exception("Given supplier doesn't exist in database",e);
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
    
    public String MaxOrderPriceCustomers() throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            return AdvancedDao.getInstance().MaxOrderPriceCustomers(con);
        }
        catch(Exception e){
            throw new Exception("There are no orders in database ",e);
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
