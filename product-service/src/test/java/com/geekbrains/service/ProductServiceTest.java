package com.geekbrains.service;

import com.geekbrains.model.Product;
import com.geekbrains.repo.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    String productName = "name";
    double productCost = 5.5;
    Product newProduct = new Product(productName,productCost);


    @Test
    public void create(){
        int size = productService.getAllProducts().size();
        productService.add(productName,productCost);
        Assertions.assertNotNull(productRepository.findById(productName));
        Assertions.assertEquals(size+1,productService.getAllProducts().size());
        Assertions.assertTrue(productService.getAllProducts().contains(newProduct));
    }

    @Test
    public void delete(){
        int size = productService.getAllProducts().size();
        System.out.println(productService.getAllProducts());
        productService.add(productName,productCost);
        System.out.println(productService.getAllProducts());
        Assertions.assertTrue(productService.delete(productName));
        Assertions.assertEquals(Optional.empty(),productRepository.findById(productName));
        Assertions.assertFalse(productService.delete(productName));
        Assertions.assertEquals(size,productService.getAllProducts().size());
        Assertions.assertFalse(productService.getAllProducts().contains(newProduct));
    }
}
