/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.CustomerDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Customer;
import java.sql.Connection;
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
    
    public void addNewCustomer(Customer customer) throws Exception
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            CustomerDao.getInstance().create(con, customer);
            
        }catch(Exception e){
            throw new Exception("Failed to add new customer",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Customer findCustomer(int customerId) throws Exception
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return CustomerDao.getInstance().find(con, customerId);
        }catch(Exception e)
        {
            throw new Exception("Failed to find customer with id " + customerId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteCustomer(int customerId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            CustomerDao.getInstance().delete(con, customerId);
        }
        catch(Exception e)
        {
            throw new Exception("Failed to delete customer with id " + customerId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateCustomer(Customer customer) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            CustomerDao.getInstance().update(con, customer);         
        }
        catch(Exception e)
        {
            throw new Exception("Failed to update cutomer " + customer, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Customer> findCustomers() throws Exception
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return CustomerDao.getInstance().findAll(con);
        }catch(Exception e)
        {
            throw new Exception("Failed to find customers",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }
}
