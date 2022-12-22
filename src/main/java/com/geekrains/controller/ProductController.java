package com.geekrains.controller;

//import com.geekrains.model.Customer;
import com.geekrains.model.CartItem;
import com.geekrains.model.Product;
//import com.geekrains.repository.CustomerRepository;
import com.geekrains.model.Role;
import com.geekrains.model.User;
import com.geekrains.repository.CartItemRepository;
import com.geekrains.repository.UserRepository;
import com.geekrains.service.CartItemService;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	ProductService productService;


	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	//return all data

//	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/allUsersPage")
	public ModelAndView allUsersPage(){
		return new ModelAndView("allUsersPage");
	}

//	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/users")
	public List<User> allUserList(){
		return userService.findAll();
	}

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

	//get role

	@GetMapping("/checkRoles")
	public Set<Role> getRole(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return userService.findByUsername(name).get().getRoles();
	}


}
