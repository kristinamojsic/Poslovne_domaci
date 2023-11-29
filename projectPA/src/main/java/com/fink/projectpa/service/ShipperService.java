/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.dao.ShipperDao;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class ShipperService {
   private static final ShipperService instance = new ShipperService();

    public ShipperService() {
    }
    
    public static ShipperService getInstance() 
    {
        return instance;
    }
    
    public void addNewShipper(Shipper shipper) throws WarehouseException
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            ShipperDao.getInstance().create(con, shipper);
            
        }catch(SQLException e){
            throw new WarehouseException("Failed to add new shipper",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Shipper findShipper(int shipperId) throws WarehouseException
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ShipperDao.getInstance().find(con, shipperId);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find shipper with id " + shipperId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteShipper(int shipperId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ShipperDao.getInstance().delete(con, shipperId);
            con.commit();
        }
        catch(SQLException e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete shipper with id " + shipperId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateShipper(Shipper shipper) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            ShipperDao.getInstance().update(con, shipper);         
        }
        catch(SQLException e)
        {
            throw new WarehouseException("Failed to update cutomer " + shipper, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Shipper> findShippers() throws WarehouseException
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ShipperDao.getInstance().findAll(con);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find shippers",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }  
}
