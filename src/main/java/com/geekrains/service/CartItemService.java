package com.geekrains.service;

import com.geekrains.model.CartItem;
import com.geekrains.model.Product;
import com.geekrains.model.User;
import com.geekrains.repository.CartItemRepository;
import com.geekrains.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public void saveOrUpdate(String productName){
        if(cartItemRepository.existsById(productName)){
            int currentQuantity = cartItemRepository.findById(productName).get().getQuantity();
            cartItemRepository.updateQuantity(currentQuantity+1,productName);
        }else{
            Product product = productService.findByName(productName);
            cartItemRepository.save(new CartItem(product.getTitle(),1,userService.findAuthenticatedUser()));
        }
    }

    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    public List<CartItem> getAllCartItemOfUser(User user){
        return cartItemRepository.findAllCartItemOfUser(user);
    }

    public void cleanCart(){
        cartItemRepository.deleteAll();
    }

    public void delete(String productName){
        cartItemRepository.deleteById(productName);
    }
}
