package com.geekrains.controller;

//import com.geekrains.model.Customer;
import com.geekrains.model.CartItem;
import com.geekrains.model.Product;
//import com.geekrains.repository.CustomerRepository;
import com.geekrains.repository.CartItemRepository;
import com.geekrains.service.CartItemService;
import com.geekrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.geekrains.repository.ProductRepository;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	ProductService productService;


	//return all data

	@GetMapping("/products")
	public List<Product> allProductsList(){
		return productService.getAllProducts();
	}

	@GetMapping("/cart")
	public List<CartItem> allCartItemsList(){
		return cartItemService.getAllCartItems();
	}

	//operations with products

	@PostMapping("/products/addNew/{title}/{cost}")
	public void addNewProduct(@PathVariable String title, @PathVariable double cost){
			productService.saveOrUpdate(title, cost);
	}

	@DeleteMapping("/products/delete/{title}")
	public void deleteProduct(@PathVariable String title){
		productService.delete(title);
	}


	//operations with cart items

	@PostMapping("/products/addToCart/{name}")
	public void addProductToCart(@PathVariable String name){
		cartItemService.saveOrUpdate(name);
	}

	@DeleteMapping("/products/deleteFromCart/{name}")
	public void deleteProductFromCart(@PathVariable String name){
		cartItemService.delete(name);
	}

}
