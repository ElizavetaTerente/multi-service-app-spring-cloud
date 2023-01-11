package com.geekbrains.service;

import com.geekbrains.Logging.LogExecutionTime;
import com.geekbrains.model.CartItem;
import com.geekbrains.model.Product;
import com.geekbrains.model.User;
import com.geekbrains.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

 //   @Autowired
 //   ProductService productService;

    @Autowired
    UserService userService;
/*
    @LogExecutionTime
    public void saveOrUpdate(String productName){
        if(cartItemRepository.existsById(productName)){
            int currentQuantity = cartItemRepository.findById(productName).get().getQuantity();
            cartItemRepository.updateQuantity(currentQuantity+1,productName);
        }else{
            Product product = productService.findByName(productName);
            cartItemRepository.save(new CartItem(product.getTitle(),1,userService.findAuthenticatedUser()));
        }
    }

 */

    @LogExecutionTime
    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    @LogExecutionTime
    public List<CartItem> getAllCartItemOfUser(User user){
        return cartItemRepository.findAllCartItemOfUser(user);
    }

    @LogExecutionTime
    public void cleanCart(){
        cartItemRepository.deleteAll();
    }

    @LogExecutionTime
    public void delete(String productName){
        cartItemRepository.deleteById(productName);
    }
}
