/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.CustomerDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class CustomerService {
    private static final CustomerService instance = new CustomerService();

    public CustomerService() {
    }
    
    public static CustomerService getInstance() 
    {
        return instance;
    }
    
    public void addNewCustomer(Customer customer) throws WarehouseException
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            CustomerDao.getInstance().create(con, customer);
            
        }catch(SQLException e){
            throw new WarehouseException("Failed to add new customer",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Customer findCustomer(int customerId) throws WarehouseException
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return CustomerDao.getInstance().find(con, customerId);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find customer with id " + customerId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteCustomer(int customerId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            CustomerDao.getInstance().delete(con, customerId);
            con.commit();
        }
        catch(SQLException e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete customer with id " + customerId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateCustomer(Customer customer) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            CustomerDao.getInstance().update(con, customer);         
        }
        catch(SQLException e)
        {
            throw new WarehouseException("Failed to update cutomer " + customer, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Customer> findCustomers() throws WarehouseException
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return CustomerDao.getInstance().findAll(con);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find customers",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }
}
