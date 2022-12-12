package com.geekrains.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class CartItem implements Serializable {

    @Id
    private String productName;

    @Column
    private int quantity;

    public CartItem() {
    }

    public CartItem(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
