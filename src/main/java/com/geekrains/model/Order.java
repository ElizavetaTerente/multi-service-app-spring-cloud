package com.geekrains.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    @JoinColumn(name="user_name", nullable=false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "title")
    @Column
    private Map<Product, Integer> itemsToBuy;

    @Column
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private LocalDateTime date;

    public Order() {
    }

    public Order(User user, Map<Product,Integer> itemsToBuy, String deliveryAddress, Status status,LocalDateTime date) {
        this.user = user;
        this.itemsToBuy = itemsToBuy;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.date = date;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product,Integer> getItemsToBuy() {
        return itemsToBuy;
    }

    public void setItemsToBuy(Map<Product,Integer> itemsToBuy) {
        this.itemsToBuy = itemsToBuy;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", itemsToBuy=" + itemsToBuy +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status=" + status +
                '}';
    }
}
