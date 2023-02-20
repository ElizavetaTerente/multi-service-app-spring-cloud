package com.geekbrains.service;

import com.geekbrains.model.Product;
import com.geekbrains.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Collection<Product> getAllProducts(){
       return productRepository.findAll();
    }

    public void add(String title,double cost){
        productRepository.save(new Product(title,cost));
    }

    public boolean delete(String title){
        Optional<Product> product = productRepository.findById(title);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return true;
        }else{
            return false;
        }
    }

}
