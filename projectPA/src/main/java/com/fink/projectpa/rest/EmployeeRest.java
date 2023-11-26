/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.EmployeeService;
import com.fink.projectpa.service.EmployeeService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PCGarage
 */
@Path("employees")
public class EmployeeRest {
    private final EmployeeService employeeService = EmployeeService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees() throws Exception {
        return employeeService.findEmployees();
        
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("id") int employeeId) throws Exception {
        return employeeService.findEmployee(employeeId);
        
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws Exception{
            employeeService.addNewEmployee(employee);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws Exception {
            employeeService.updateEmployee(employee);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") int employeeId) throws Exception {
            employeeService.deleteEmployee(employeeId);
            return Response.ok().build();
    }
    
    //advanced 
    @GET
    @Path("/maxOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public String MaxOrderPriceEmployee() throws Exception
    {
        return advancedService.MaxOrderPriceEmployee();
    }
}
