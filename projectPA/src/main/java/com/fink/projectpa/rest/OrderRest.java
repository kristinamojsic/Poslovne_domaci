/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetails;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.OrderService;
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
@Path("orders")
public class OrderRest {
    private final OrderService orderService = OrderService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetails> getOrders() throws Exception {
        return orderService.findOrders();
        
    } 
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetails getOrderById(@PathParam("id") int orderId) throws Exception {
        return orderService.findOrder(orderId);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderDetails orderDetails) throws Exception{
            orderService.addNewOrder(orderDetails);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderDetails orderDetails) throws Exception {
            orderService.updateOrder(orderDetails);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("id") int orderId) throws Exception {
            orderService.deleteOrder(orderId);
            return Response.ok().build();
    }
    //advanced queries
    
    
    @GET
    @Path("/totalPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public double totalPriceOrders() throws Exception
    {
        return advancedService.totalPriceOrders();
    }
    
    
    
    
}

