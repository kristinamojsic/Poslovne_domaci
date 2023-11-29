/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa;
import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.dao.OrderDetailsDao;
import com.fink.projectpa.dao.ShipperDao;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetails;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.service.AdvancedService;
import com.fink.projectpa.service.CustomerService;
import com.fink.projectpa.service.EmployeeService;
import com.fink.projectpa.service.OrderService;
import com.fink.projectpa.service.ProductService;
import com.fink.projectpa.service.ShipperService;
import com.fink.projectpa.service.SupplierService;
import java.util.List;
/**
 *
 * @author PCGarage
 */
public class Warehouse {
    public static void main(String[] args) {
        System.out.println("Hello");
        try
        {
            /* *** CUSTOMERS *** */
            System.out.println(CustomerService.getInstance().findCustomer(1));
            
            //System.out.println(CustomerService.getInstance().findCustomer(5));
            //System.out.println(CustomerService.getInstance().findCustomers());
            //Customer customer = new Customer(1,"Novi","novo","novo","novo","novo","novo");
            //CustomerService.getInstance().addNewCustomer(customer);
            //CustomerService.getInstance().deleteCustomer(10);
            
            //Customer forUpdate = CustomerService.getInstance().findCustomer(1);
            //forUpdate.setAddress("Atinska 55");
            //CustomerService.getInstance().updateCustomer(forUpdate);
            
            /* *** EMPLOYEES *** */
            
            //System.out.println(EmployeeService.getInstance().findEmployee(1));
            //System.out.println(EmployeeService.getInstance().findEmployee(5));
            //System.out.println(EmployeeService.getInstance().findEmployees());
            //Employee employee = new Employee("Novi","novo","2020-08-08");
            //EmployeeService.getInstance().addNewEmployee(employee);
            //EmployeeService.getInstance().deleteEmployee(4);
            //Employee forUpdate = EmployeeService.getInstance().findEmployee(1);
            //forUpdate.setLastName("Rakic");
            //EmployeeService.getInstance().updateEmployee(forUpdate);
            /* *** SUPPLIERS ***/
            //System.out.println(SupplierService.getInstance().findSupplier(1));
            //System.out.println(SupplierService.getInstance().findSupplier(5));
            //System.out.println(SupplierService.getInstance().findSuppliers());
            //Supplier supplier = new Supplier(-1,"Novi","novo","novo","novo","novo","novo","novo");
            //SupplierService.getInstance().addNewSupplier(supplier);
            //SupplierService.getInstance().deleteSupplier(3);
            //Supplier forUpdate = SupplierService.getInstance().findSupplier(1);
            //forUpdate.setContactPerson("Milos jovic");
            //SupplierService.getInstance().updateSupplier(forUpdate);
            
            
            /* ***SHIPPERS *** */
            //System.out.println(ShipperService.getInstance().findShipper(1));
            //System.out.println(ShipperService.getInstance().findShipper(5));
            //System.out.println(ShipperService.getInstance().findShippers());
            //Shipper shipper = new Shipper(-1,"Novi","novo");
            //ShipperService.getInstance().addNewShipper(shipper);
           // ShipperService.getInstance().deleteShipper(4);
            //Shipper forUpdate = ShipperService.getInstance().findShipper(1);
            //forUpdate.setPhone("0622222222");
            //ShipperService.getInstance().updateShipper(forUpdate);
            
            
            /* *** PRODUCTS *** */
            //System.out.println(ProductService.getInstance().findProduct(1));
            //System.out.println(ProductService.getInstance().findProduct(5));
            //System.out.println(ProductService.getInstance().findProducts());
            //Supplier supplier = SupplierService.getInstance().findSupplier(1);
            //Product product = new Product(-1,"Novi",supplier,"novo",1252);
            //ProductService.getInstance().addNewProduct(product);
             //ProductService.getInstance().deleteProduct(5);
            //Product forUpdate = ProductService.getInstance().findProduct(1);
            //forUpdate.setPricePerUnit(12020);
            //ProductService.getInstance().updateProduct(forUpdate);
            
            
            /* *** ORDERS *** */
            //Order order = OrderService.getInstance().findOrder(1).getOrder();
            //System.out.println(ShipperService.getInstance().findShipper(order.getShipper().getShipperId()));
            //System.out.println(ShipperDao.getInstance().find(con, order.getShipper().getShipperId());)
            
            /* ** ADVANCED *** */
            
           //System.out.println(AdvancedService.getInstance().CustomersAndOrders());
           //System.out.print(AdvancedService.getInstance().suppliersProducts(1).get(0).getSupplier().getSupplierId());
           //System.out.println(AdvancedService.getInstance().suppliersProducts(1)); 
           //System.out.println(AdvancedService.getInstance().totalPriceOrders());
           //System.out.println(AdvancedService.getInstance().totalPriceForCustomer(1));
           //System.out.println(AdvancedService.getInstance().totalPriceForShipper(1));
           //System.out.println(AdvancedService.getInstance().totalPriceForSupplier(1));
           //System.out.println(AdvancedService.getInstance().shippersProducts(1));
          // System.out.println(AdvancedService.getInstance().MaxOrderPriceCustomers());
          //System.out.println(AdvancedService.getInstance().MostOrdersProducts());
         /* List <OrderDetails> orders = OrderService.getInstance().findOrders();
          for(OrderDetails order : orders)
            {
                if(order.getOrder()!=null)
                {
                    if(order.getOrder().getCustomer().getCustomerID() == 1)
                {
                    System.out.println(order.getProduct().getPricePerUnit()*order.getQuantity());    
                }
                }
            }*/
         
         System.out.println(AdvancedService.getInstance().CustomersAndOrders());
        }
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}
