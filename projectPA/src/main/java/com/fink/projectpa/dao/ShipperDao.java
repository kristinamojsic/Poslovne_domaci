/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;
import com.fink.projectpa.data.Shipper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PCGarage
 */
public class ShipperDao {
private static final ShipperDao instance = new ShipperDao();
   
   private ShipperDao(){}
   
   public static ShipperDao getInstance()
   {
       return instance;
   }
   public void create(Connection con, Shipper shipper) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement("INSERT INTO shippers(ShipperName, Phone) VALUES(?,?)");
            ps.setString(1, shipper.getShipperName());
            ps.setString(2, shipper.getPhone());
            ps.executeUpdate();
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
       
    }
   
   public void update(Connection con, Shipper shipper) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE shippers SET ShipperName=?, Phone=? WHERE ShipperId=?");
            ps.setString(1, shipper.getShipperName());
            ps.setString(2, shipper.getPhone());
            ps.setInt(3, shipper.getShipperId());
            
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idShipper) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            OrderDao.getInstance().deleteForShipper(con, idShipper);
            ps = con.prepareStatement("DELETE FROM shippers WHERE ShipperId=?");
            
            ps.setInt(1, idShipper);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   
   public Shipper find(Connection con, int shipperId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       Shipper shipper = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM shippers WHERE ShipperId=?");
           ps.setInt(1,shipperId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               shipper = new Shipper(rs.getInt("ShipperId"),rs.getString("ShipperName"),rs.getString("Phone"));
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipper;
    }
//proveriti implementaciju
   
   public List<Shipper> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Shipper> shipperList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM shippers");
           rs = ps.executeQuery();
           while(rs.next())
           {
               shipperList.add(new Shipper(rs.getInt("ShipperId"),rs.getString("ShipperName"),rs.getString("Phone")));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return shipperList;
       
       
   }       
}
