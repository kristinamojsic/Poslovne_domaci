/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Employee;
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
public class EmployeeDao {
    private static final EmployeeDao instance = new EmployeeDao();
   
   private EmployeeDao(){}
   
   public static EmployeeDao getInstance()
   {
       return instance;
   }
   public void create(Connection con, Employee employee) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
       // int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO employees(LastName, FirstName, BirthDate) VALUES(?,?,?)");
            
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getBirthDate());
            System.out.println(ps);
            ps.executeUpdate();
            
            //rs = ps.getGeneratedKeys();
            //rs.next();
            //id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
       // return id;
    }
   
   public void update(Connection con, Employee employee) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE employees SET LastName=?, FirstName=?, BirthDate=? WHERE EmployeeId=?");
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getBirthDate());
            ps.setInt(4, employee.getEmployeeId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
  
   public void delete(Connection con, int idEmployee) throws SQLException
   {
       PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM employees WHERE EmployeeId=?");
            //dobiti informaciju da li je obrisao?
            ps.setInt(1, idEmployee);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
   }
   
   public Employee find(Connection con, int employeeId) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       Employee employee = null;
       try
       {
           ps = con.prepareStatement("SELECT * FROM employees WHERE EmployeeId=?");
           ps.setInt(1,employeeId);
           rs = ps.executeQuery();
           if(rs.next())
           {
               employee = new Employee(rs.getInt("EmployeeId"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("BirthDate"));
               
           }
       }finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employee;
    }
//proveriti implementaciju
   
   public List<Employee> findAll(Connection con) throws SQLException
   {
       PreparedStatement ps = null;
       ResultSet rs = null;
       List<Employee> employeeList = new ArrayList<>();
       
       try
       {
           ps = con.prepareStatement("SELECT * FROM employees");
           rs = ps.executeQuery();
           while(rs.next())
           {
               employeeList.add(new Employee(rs.getInt("EmployeeId"),rs.getString("LastName"),rs.getString("FirstName"),rs.getString("BirthDate")));
               
           }
       }
       finally{
           ResourcesManager.closeResources(rs, ps);
       }
       return employeeList;    
}
}
