package com.geekbrains.controller;

import com.geekbrains.model.CartItem;
import com.geekbrains.repo.CartRepository;
import com.geekbrains.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CartControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    String newItemName = "newItem";
    String userName = "user";

    @Test
    public void allProducts() {
        Collection<CartItem> items = webTestClient.get()
                .uri("/cart/" + userName)
                .exchange()
                .expectBody(Collection.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertEquals(items.size(),cartRepository.findAll().size());
    }
}
