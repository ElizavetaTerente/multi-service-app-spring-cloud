package com.geekbrains.controller;

import com.geekbrains.model.CartItem;
import com.geekbrains.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/cart")
    public List<CartItem> allCartItemsList(){
        return cartItemService.getAllCartItems();
    }
/*
    @PostMapping("/cart/{name}")
    public void addProductToCart(@PathVariable String name){
        cartItemService.saveOrUpdate(name);
    }

 */

    @DeleteMapping("/cart/{name}")
    public void deleteProductFromCart(@PathVariable String name){
        cartItemService.delete(name);
    }
}
