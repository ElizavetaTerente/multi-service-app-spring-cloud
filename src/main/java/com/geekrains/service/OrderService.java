package com.geekrains.service;

import com.geekrains.Logging.LogExecutionTime;
import com.geekrains.model.*;
import com.geekrains.repository.OrderRepository;
import com.geekrains.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @LogExecutionTime
    public void placeOrder() {
        User user = userService.findAuthenticatedUser();
        String deliveryAddress = user.getAdress();
        Set<CartItem> itemsInCard = new HashSet<>(cartItemService.getAllCartItemOfUser(user));

        Map<Product,Integer> itemsToPutInOrder = new HashMap<>();

        String productName;
        int quantity;
        Product product;

        for (CartItem item :itemsInCard) {
            productName = item.getProductName();
            product = productService.findByName(productName);
            quantity = item.getQuantity();
            itemsToPutInOrder.put(product,quantity);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);;

        Order order = orderRepository.save(new Order(user,itemsToPutInOrder,deliveryAddress,Status.NOT_PAID,now));
        cartItemService.cleanCart();
    }

    @LogExecutionTime
    public Set<Order> findOrdersOfUser(){
        User user = userService.findAuthenticatedUser();
        return orderRepository.findAllOrdersOfUser(user);
    }

    @LogExecutionTime
    public Order findOrderById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        }else{
            throw  new IllegalArgumentException("no order with such an id");
        }
    }

    @LogExecutionTime
    public List<Order> findAll(){
        return orderRepository.findAll();
    }



}
