/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.CustomerService;
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
@Path("customers")
public class CustomerRest {
    private final CustomerService customerService = CustomerService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() throws Exception {
        return customerService.findCustomers();
        
    } 
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("id") int customerId) throws Exception {
        return customerService.findCustomer(customerId);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws Exception{
            customerService.addNewCustomer(customer);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) throws Exception {
            customerService.updateCustomer(customer);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") int customerId) throws Exception {
            customerService.deleteCustomer(customerId);
            return Response.ok().build();
    }
    //advanced queries
    
    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public String CustomersAndOrders() throws Exception
    {
        return advancedService.CustomersAndOrders();
    }
    
    @GET
    @Path("/{id}/totalPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public double totalPriceForCustomer(@PathParam("id") int customerId) throws Exception
    {
        return advancedService.totalPriceForCustomer(customerId);
    }
    @GET
    @Path("/maxOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public String MaxOrderPriceCustomers() throws Exception
    {
        return advancedService.MaxOrderPriceCustomers();
    }
    
    
    
    
}
