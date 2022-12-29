package com.geekrains.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartItem implements Serializable {

    @Id
    private String productName;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name="user_name", nullable=false)
    private User user;

    public CartItem() {
    }

    public CartItem(String productName, int quantity, User user) {
        this.productName = productName;
        this.quantity = quantity;
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(productName, cartItem.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }
}
