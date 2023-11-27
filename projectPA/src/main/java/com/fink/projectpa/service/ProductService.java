/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ProductDao;
import com.fink.projectpa.dao.ResourcesManager;
import com.fink.projectpa.data.Product;
import java.sql.Connection;
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
    
    public void addNewProduct(Product product) throws Exception
    {
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            
            ProductDao.getInstance().create(con, product);
            
        }catch(Exception e){
            throw new Exception("Failed to add new product",e);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Product findProduct(int productId) throws Exception
    {
        Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ProductDao.getInstance().find(con, productId);
        }catch(Exception e)
        {
            throw new Exception("Failed to find product with id " + productId,e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteProduct(int productId) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ProductDao.getInstance().delete(con, productId); 
        }
        catch(Exception e)
        {
            ResourcesManager.rollbackTransactions(con);
            throw new Exception("Failed to delete product with id " + productId,e);
            
        }
        finally
        {
            ResourcesManager.closeConnection(con);
            
        }    
    }
    
    public void updateProduct(Product product) throws Exception
    {
        Connection con = null;
        try
        {
            con = ResourcesManager.getConnection();
            ProductDao.getInstance().update(con, product);         
        }
        catch(Exception e)
        {
            throw new Exception("Failed to update cutomer " + product, e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Product> findProducts() throws Exception
    {Connection con = null;
        try
        {
           con = ResourcesManager.getConnection();
           return ProductDao.getInstance().findAll(con);
        }catch(Exception e)
        {
            throw new Exception("Failed to find products",e);
        }
        finally 
        {
            ResourcesManager.closeConnection(con);
        }
        
    }    
}
