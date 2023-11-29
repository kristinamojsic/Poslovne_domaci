/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ProductDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PCGarage
 */
public class ProductService {
    private static final ProductService instance = new ProductService();

    public ProductService() {
    }
    
    public static ProductService getInstance() 
    {
        return instance;
    }
    
    public void addNewProduct(Product product) throws WarehouseException
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            ProductDao.getInstance().create(con, product);
            
        }catch(SQLException e){
            throw new WarehouseException("Failed to add new product",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Product findProduct(int productId) throws WarehouseException
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ProductDao.getInstance().find(con, productId);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find product with id " + productId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteProduct(int productId) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ProductDao.getInstance().delete(con, productId); 
            con.commit();
        }
        catch(SQLException e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete product with id " + productId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateProduct(Product product) throws WarehouseException
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            ProductDao.getInstance().update(con, product);         
        }
        catch(SQLException e)
        {
            throw new WarehouseException("Failed to update cutomer " + product, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Product> findProducts() throws WarehouseException
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ProductDao.getInstance().findAll(con);
        }catch(SQLException e)
        {
            throw new WarehouseException("Failed to find products",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }    
}
