package com.geekbrains.controller;

import com.geekbrains.model.Product;
import com.geekbrains.repo.ProductRepository;
import com.geekbrains.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PSControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    String productName = "name";
    double productCost = 5.5;

    @Test
    public void allProducts() {
        Collection<Product> products = webTestClient.get()
                .uri("/products")
                .exchange()
                .expectBody(Collection.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertEquals(products.size(),productService.getAllProducts().size());
    }

    @Test
    public void deleteProduct(){
        productService.add(productName,productCost);
        Assertions.assertEquals(200,webTestClient.delete()
                .uri("/products/delete/" + productName).exchange()
                .expectBody().returnResult().getStatus().value());

        Assertions.assertEquals(400,webTestClient.delete()
                .uri("/products/delete/" + productName).exchange()
                .expectBody().returnResult().getStatus().value());
    }


}
