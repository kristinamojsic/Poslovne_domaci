/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.dao.SupplierDao;
import com.fink.projectpa.data.Supplier;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class SupplierService {
    private static final SupplierService instance = new SupplierService();

    public SupplierService() {
    }
    
    public static SupplierService getInstance() 
    {
        return instance;
    }
    
    public void addNewSupplier(Supplier supplier) throws Exception
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            SupplierDao.getInstance().create(con, supplier);
            
        }catch(Exception e){
            throw new Exception("Failed to add new supplier",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Supplier findSupplier(int supplierId) throws Exception
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return SupplierDao.getInstance().find(con, supplierId);
        }catch(Exception e)
        {
            throw new Exception("Failed to find supplier with id " + supplierId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteSupplier(int supplierId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            SupplierDao.getInstance().delete(con, supplierId);
            con.commit();
        }
        catch(Exception e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new Exception("Failed to delete supplier with id " + supplierId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateSupplier(Supplier supplier) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            SupplierDao.getInstance().update(con, supplier);         
        }
        catch(Exception e)
        {
            throw new Exception("Failed to update cutomer " + supplier, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Supplier> findSuppliers() throws Exception
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return SupplierDao.getInstance().findAll(con);
        }catch(Exception e)
        {
            throw new Exception("Failed to find suppliers",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    } 
}
