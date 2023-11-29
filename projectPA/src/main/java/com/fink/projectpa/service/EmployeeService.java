/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.EmployeeDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
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
    
    public void addNewEmployee(Employee employee) throws WarehouseException
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            EmployeeDao.getInstance().create(con, employee);
            
        }catch(SQLException e){
            throw new WarehouseException("Failed to add new customer",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Employee findEmployee(int employeeId) throws WarehouseException
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return EmployeeDao.getInstance().find(con, employeeId);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find customer with id " + employeeId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteEmployee(int employeeId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            EmployeeDao.getInstance().delete(con, employeeId);
            con.commit();
        }
        catch(SQLException e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete customer with id " + employeeId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateEmployee(Employee employee) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            EmployeeDao.getInstance().update(con, employee);         
        }
        catch(SQLException e)
        {
            throw new WarehouseException("Failed to update employee " + employee, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Employee> findEmployees() throws WarehouseException
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return EmployeeDao.getInstance().findAll(con);
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
