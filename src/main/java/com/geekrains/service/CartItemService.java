package com.geekrains.service;

import com.geekrains.model.CartItem;
import com.geekrains.model.Product;
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
    ProductRepository productRepository;

    public void saveOrUpdate(String productName){
        if(cartItemRepository.existsById(productName)){
            int currentQuantity = cartItemRepository.findById(productName).get().getQuantity();
            cartItemRepository.updateQuantity(currentQuantity+1,productName);
        }else{
            Product product = productRepository.findById(productName).get();
            cartItemRepository.save(new CartItem(product.getTitle(),1));
        }
    }

    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    public void delete(String productName){
        cartItemRepository.deleteById(productName);
    }
}
