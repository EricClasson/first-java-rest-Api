package com.example.restful_service_test.model;

import java.math.BigDecimal;

public class Product {
    private  String id;
    private  String description;
    private  BigDecimal price;

    public Product(final String id, final String description, final BigDecimal price){
        this.id = id;
        this.description = description;
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
