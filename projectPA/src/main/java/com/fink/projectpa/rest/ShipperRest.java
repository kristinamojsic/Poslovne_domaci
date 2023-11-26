/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.ShipperService;
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
@Path("shippers")
public class ShipperRest {
    private final ShipperService shipperService = ShipperService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getShippers() throws Exception {
        return shipperService.findShippers();
        
    } 
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipper getShipperById(@PathParam("id") int shipperId) throws Exception {
        return shipperService.findShipper(shipperId);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addShipper(Shipper shipper) throws Exception{
            shipperService.addNewShipper(shipper);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipper(Shipper shipper) throws Exception {
            shipperService.updateShipper(shipper);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShipper(@PathParam("id") int shipperId) throws Exception {
            shipperService.deleteShipper(shipperId);
            return Response.ok().build();
    }
    //advanced queries
    
    @GET
    @Path("/{id}/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Product> shippersProducts(@PathParam("id") int shipperId) throws Exception
    {
        return advancedService.shippersProducts(shipperId);
    }
    
    @GET
    @Path("/{id}/totalPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public double totalPriceForShipper(@PathParam("id") int shipperId) throws Exception
    {
        return advancedService.totalPriceForShipper(shipperId);
    }
    
}
