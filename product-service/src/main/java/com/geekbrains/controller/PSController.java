package com.geekbrains.controller;

import com.geekbrains.model.Product;
import com.geekbrains.repo.ProductRepository;
import com.geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class PSController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/products/add/{title}/{cost}")
    public void add(@PathVariable String title,@PathVariable double cost){
        productService.add(title,cost);
    }
    @DeleteMapping("/products/delete/{title}")
    public ResponseEntity<Boolean> delete(@PathVariable String title){

        if(productService.delete(title)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);
        }
    }

}
