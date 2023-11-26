/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.SupplierService;
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
@Path("suppliers")
public class SupplierRest {
    private final SupplierService supplierService = SupplierService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getSuppliers() throws Exception {
        return supplierService.findSuppliers();
        
    } 
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getSupplierById(@PathParam("id") int supplierId) throws Exception {
        return supplierService.findSupplier(supplierId);
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Supplier supplier) throws Exception{
            supplierService.addNewSupplier(supplier);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Supplier supplier) throws Exception {
            supplierService.updateSupplier(supplier);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@PathParam("id") int supplierId) throws Exception {
            supplierService.deleteSupplier(supplierId);
            return Response.ok().build();
    }
    //advanced queries
    
    @GET
    @Path("/{id}/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> suppliersProducts(@PathParam("id") int supplierId) throws Exception
    {
        return advancedService.suppliersProducts(supplierId);
    }
    
    @GET
    @Path("/{id}/totalPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public double totalPriceForSupplier(@PathParam("id") int supplierId) throws Exception
    {
        return advancedService.totalPriceForSupplier(supplierId);
    }
    @GET
    @Path("/maxOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public String MaxOrderPriceSuppliers() throws Exception
    {
        return advancedService.MaxOrderPriceSupplier();
    }
    
    
    
    
}