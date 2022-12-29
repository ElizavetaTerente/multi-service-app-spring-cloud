package com.geekrains.controller;

//import com.geekrains.model.Customer;
import com.geekrains.model.*;
//import com.geekrains.repository.CustomerRepository;
import com.geekrains.repository.CartItemRepository;
import com.geekrains.repository.UserRepository;
import com.geekrains.service.CartItemService;
import com.geekrains.service.OrderService;
import com.geekrains.service.ProductService;
import com.geekrains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.geekrains.repository.ProductRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;


	@GetMapping("/products")
	public List<Product> allProductsList(){
		return productService.getAllProducts();
	}

	@PostMapping("/products/addNew/{title}/{cost}")
	public void addNewProduct(@PathVariable String title, @PathVariable double cost){
			productService.saveOrUpdate(title, cost);
	}

	@DeleteMapping("/products/delete/{title}")
	public void deleteProduct(@PathVariable String title){
		productService.delete(title);
	}

}
