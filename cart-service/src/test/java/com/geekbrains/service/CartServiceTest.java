package com.geekbrains.service;

import com.geekbrains.model.CartItem;
import com.geekbrains.repo.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    String newItemName = "newItem";
    String userName = "user";

    @Test
    public void create(){
        //new item
        int size = cartRepository.findAll().size();
        int sizeOfUserCart = cartService.cart(userName).size();

        cartService.addToCart(userName,newItemName);
        Assertions.assertTrue(cartRepository.existsByUsernameAndTitle(userName,newItemName));
        Assertions.assertEquals(size+1,cartRepository.findAll().size());
        Assertions.assertEquals(sizeOfUserCart+1,cartRepository.findAllByUsername(userName).size());
        Assertions.assertTrue(cartService.cart(userName).contains(cartRepository.findFirstByUsernameAndTitle(userName,newItemName)));

        //item exist and we need to quantity = quantity+1
        cartService.addToCart(userName,newItemName);
        Assertions.assertEquals(size+1,cartRepository.findAll().size());
        Assertions.assertEquals(2,cartRepository.findFirstByUsernameAndTitle(userName,newItemName).getQuantity());
    }

    @Test
    public void deleteItemTest(){
        int size = cartRepository.findAll().size();
        cartService.addToCart(userName,newItemName);
        cartService.deleteItem(userName,newItemName);
        Assertions.assertFalse(cartRepository.existsByUsernameAndTitle(userName,newItemName));
        Assertions.assertEquals(size,cartRepository.findAll().size());
        Assertions.assertFalse(cartService.cart(userName).contains(cartRepository.findFirstByUsernameAndTitle(userName,newItemName)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, -15})
    public void changeQuantityTest(int number){
        cartService.addToCart(userName,newItemName);
        CartItem cartItem = cartRepository.findFirstByUsernameAndTitle(userName,newItemName);
        int prevQuantity = cartItem.getQuantity();
        cartService.changeQuantity(userName,newItemName,number);
        cartItem = cartRepository.findFirstByUsernameAndTitle(userName,newItemName);
        Assertions.assertEquals(prevQuantity + number,cartItem.getQuantity());
    }
}
