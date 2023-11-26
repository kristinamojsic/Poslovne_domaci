/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

/**
 *
 * @author PCGarage
 */
public class Product {
    private int productId = -1;
    private String productName;
    private Supplier supplier;
    private String ProductCategory;
    private double pricePerUnit;

    public Product() {
    }

    public Product(int productId, String productName, Supplier supplier, String ProductCategory, double pricePerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.supplier = supplier;
        this.ProductCategory = ProductCategory;
        this.pricePerUnit = pricePerUnit;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String ProductCategory) {
        this.ProductCategory = ProductCategory;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Products{");
        sb.append("productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", supplier=").append(supplier);
        sb.append(", ProductCategory=").append(ProductCategory);
        sb.append(", pricePerUnit=").append(pricePerUnit);
        sb.append('}');
        return sb.toString();
    }
    
}
