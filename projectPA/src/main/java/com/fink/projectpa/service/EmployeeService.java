/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.EmployeeDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Employee;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class EmployeeService {
    private static final EmployeeService instance = new EmployeeService();

    public EmployeeService() {
    }
    
    public static EmployeeService getInstance() 
    {
        return instance;
    }
    
    public void addNewEmployee(Employee employee) throws Exception
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            EmployeeDao.getInstance().create(con, employee);
            
        }catch(Exception e){
            throw new Exception("Failed to add new customer",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Employee findEmployee(int employeeId) throws Exception
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return EmployeeDao.getInstance().find(con, employeeId);
        }catch(Exception e)
        {
            throw new Exception("Failed to find customer with id " + employeeId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteEmployee(int employeeId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            EmployeeDao.getInstance().delete(con, employeeId);
        }
        catch(Exception e)
        {
            throw new Exception("Failed to delete customer with id " + employeeId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateEmployee(Employee employee) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            EmployeeDao.getInstance().update(con, employee);         
        }
        catch(Exception e)
        {
            throw new Exception("Failed to update employee " + employee, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Employee> findEmployees() throws Exception
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return EmployeeDao.getInstance().findAll(con);
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
