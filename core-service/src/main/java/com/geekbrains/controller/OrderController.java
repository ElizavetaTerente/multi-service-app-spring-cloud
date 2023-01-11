package com.geekbrains.controller;

import com.geekbrains.model.Order;
import com.geekbrains.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    /*
    @PostMapping("/placeOrder")
    public void placeOrder(){
        orderService.placeOrder();
    }

     */

    @GetMapping("/user/orders")
    public Set<Order> loadOrders(){
        return orderService.findOrdersOfUser();
    }
}
