/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.dao.ShipperDao;
import com.fink.projectpa.data.Shipper;
import java.sql.Connection;
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
    
    public void addNewShipper(Shipper shipper) throws Exception
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            ShipperDao.getInstance().create(con, shipper);
            
        }catch(Exception e){
            throw new Exception("Failed to add new shipper",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Shipper findShipper(int shipperId) throws Exception
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ShipperDao.getInstance().find(con, shipperId);
        }catch(Exception e)
        {
            throw new Exception("Failed to find shipper with id " + shipperId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteShipper(int shipperId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            ShipperDao.getInstance().delete(con, shipperId);
        }
        catch(Exception e)
        {
            throw new Exception("Failed to delete shipper with id " + shipperId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateShipper(Shipper shipper) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            ShipperDao.getInstance().update(con, shipper);         
        }
        catch(Exception e)
        {
            throw new Exception("Failed to update cutomer " + shipper, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Shipper> findShippers() throws Exception
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ShipperDao.getInstance().findAll(con);
        }catch(Exception e)
        {
            throw new Exception("Failed to find shippers",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }  
}
