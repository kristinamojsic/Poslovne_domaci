/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Product;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.ProductService;
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
@Path("products")
public class ProductRest {
    private final ProductService customerService = ProductService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() throws Exception {
        return customerService.findProducts();
        
    } 
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") int customerId) throws Exception {
        return customerService.findProduct(customerId);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product customer) throws Exception{
            customerService.addNewProduct(customer);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product customer) throws Exception {
            customerService.updateProduct(customer);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int customerId) throws Exception {
            customerService.deleteProduct(customerId);
            return Response.ok().build();
    }
    //advanced queries
       
    @GET
    @Path("/mostOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public String MostOrdersProducts() throws Exception
    {
        return advancedService.MostOrdersProducts();
    }
    
    
    
    
}

