package com.geekbrains.service;

//import com.geekbrains.integration.CSIntegrationService;
import com.geekbrains.model.CartItem;
import com.geekbrains.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

  /*  @Autowired
    CSIntegrationService csIntegrationService;
   */

    public Collection<CartItem> cart(String username){
            return cartRepository.findAllByUsername(username);
    }

    public void addToCart(String username, String title){
        if(cartRepository.existsByUsernameAndTitle(username,title)){
            CartItem cartItem = cartRepository.findFirstByUsernameAndTitle(username, title);
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartRepository.save(cartItem);
        }else{
            cartRepository.save(new CartItem(title,1,username));
        }
    }

    public void deleteItem(String username, String title){
        CartItem cartItem = cartRepository.findFirstByUsernameAndTitle(username, title);
        cartRepository.delete(cartItem);
    }

    public void changeQuantity(String username, String title,int number){
        CartItem cartItem = cartRepository.findFirstByUsernameAndTitle(username, title);
        cartItem.setQuantity(cartItem.getQuantity()+number);
        cartRepository.save(cartItem);
    }

}
