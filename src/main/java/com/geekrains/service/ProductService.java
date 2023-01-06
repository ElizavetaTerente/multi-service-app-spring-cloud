package com.geekrains.service;

//import com.geekrains.repository.CustomerRepository;
import com.geekrains.Logging.LogExecutionTime;
import com.geekrains.exceptions.AddNewProductException;
import com.geekrains.model.Product;
import com.geekrains.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @LogExecutionTime
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @LogExecutionTime
    public void saveOrUpdate(String title,double cost) {
        if(cost <= 0){throw new AddNewProductException("price cannot be less than or equals 0");
        }else if(productRepository.existsById(title)){
            throw new AddNewProductException("product with such a name exists already");
        }
        productRepository.save(new Product(title, cost));
    }
    @LogExecutionTime
    public void delete(String title){
        productRepository.deleteById(title);
    }

    @LogExecutionTime
    public Product findByName(String title){
        Optional<Product> product = productRepository.findById(title);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new IllegalArgumentException("product doesnt exist!");
        }
    }
}
