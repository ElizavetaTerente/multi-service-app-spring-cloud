package com.geekbrains.controller;

import com.geekbrains.model.CartItem;
import com.geekbrains.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart/{username}")
    public Collection<CartItem> getAllProducts(@PathVariable String username){
        return cartService.cart(username);
    }
    @PostMapping("/cart/{username}/{title}")
    public void addToCard(@PathVariable String username,@PathVariable String title){
        cartService.addToCart(username,title);
    }

    @DeleteMapping("/cart/{username}/{title}")
    public void deleteItem(@PathVariable String username,@PathVariable String title){
        cartService.deleteItem(username,title);
    }

    @PostMapping("/cart/{username}/{title}/{number}")
    public void changeQuantity(@PathVariable String username,@PathVariable String title,@PathVariable int number){
        cartService.changeQuantity(username,title,number);
    }

}
