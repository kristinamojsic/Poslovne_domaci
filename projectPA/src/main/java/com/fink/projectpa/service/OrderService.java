/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.dao.OrderDetailsDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetails;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class OrderService {
    private static final OrderService instance = new OrderService();

    public OrderService() {
    }
    
    public static OrderService getInstance() 
    {
        return instance;
    }
    
    public void addNewOrder(OrderDetails orderDetails) throws WarehouseException
    {
        Connection con = null;
        try{
            
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            //OrderDao.getInstance().create(con, );
            OrderDetailsDao.getInstance().create(con, orderDetails);
            con.commit();
            
        }catch(SQLException e){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new order",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public OrderDetails findOrder(int orderId) throws WarehouseException
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return OrderDetailsDao.getInstance().find(con, orderId);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find order with id " + orderId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteOrder(int orderId) throws WarehouseException
    {
        Connection con = null;
        
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            OrderDao.getInstance().delete(con, orderId);
            OrderDetailsDao.getInstance().deleteForOrder(con, orderId);
            con.commit();
        }
        catch(SQLException e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order with id " + orderId,e);
            
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateOrder(OrderDetails orderDetails) throws WarehouseException
    {
        Connection con = null;
        try
        {
            
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            OrderDao.getInstance().update(con, orderDetails.getOrder());
            OrderDetailsDao.getInstance().update(con, orderDetails);  
            con.commit();
        }
        catch(SQLException e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order " + orderDetails, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<OrderDetails> findOrders() throws WarehouseException
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return OrderDetailsDao.getInstance().findAll(con);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find orders",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }   
    /*public List<Order> findAll() throws WarehouseException
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return OrderDao.getInstance().findAll(con);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find orders",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }   */
}
