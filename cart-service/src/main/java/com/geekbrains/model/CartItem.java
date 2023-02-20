package com.geekbrains.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "cart")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private Long itemId;

    @Column
    private String title;

    @Column
    private int quantity;

    @Column
    private String username;

    public CartItem() {
    }

    public CartItem(Long itemId, String title, int quantity, String username) {
        this.itemId = itemId;
        this.title = title;
        this.quantity = quantity;
        this.username = username;
    }

    public CartItem(String title, int quantity, String username) {
        this.title = title;
        this.quantity = quantity;
        this.username = username;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", username='" + username + '\'' +
                '}';
    }
}