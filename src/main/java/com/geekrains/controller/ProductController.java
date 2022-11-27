package com.geekrains.controller;

import com.geekrains.model.Customer;
import com.geekrains.model.Product;
import com.geekrains.repository.CustomerRepository;
import com.geekrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.geekrains.repository.ProductRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductService productService;

	@GetMapping("/check")
	@ResponseBody
	public String checkDB(){
		Customer customer1 = customerRepository.findById(1).get();
		Customer customer2 = customerRepository.findById(2).get();

		Product product1 = productRepository.findById(1).get();
		Product product2 = productRepository.findById(2).get();
		Product product3 = productRepository.findById(3).get();

		customer1.getBoughtProducts().add(product1);
		customer1.getBoughtProducts().add(product2);

		customer2.getBoughtProducts().add(product2);
		customer2.getBoughtProducts().add(product3);

		customerRepository.save(customer1);
		customerRepository.save(customer2);

		return "check";
	}

	@GetMapping("/filteredProductsPage")
	public String filterProductsPage(Model model){
		String type = "";
		double value = 0;
		model.addAttribute("type", type);
		model.addAttribute("value", value);
		return "filteredProductsPage";
	}

	@PostMapping("/filteredProductsPage")
	public String filterProductsPage(@RequestParam("type") String type,@RequestParam("value") double value,Model model) {
		if(type.equals("min")){
			model.addAttribute("productsList",productRepository.findAll().stream().filter(p->p.getCost() < value).collect(Collectors.toList()));
		}else if(type.equals("max")){
			model.addAttribute("productsList",productRepository.findAll().stream().filter(p->p.getCost() > value).collect(Collectors.toList()));
		}
		return "filteredProductsPage";
	}

	@GetMapping("/deleteProductByIdPage")
	public String deleteProductById(Model model){
		int id = 0;
		model.addAttribute("id", id);
		return "deleteProductByIdPage";
	}

	@PostMapping("/deleteProductByIdPage")
	@ResponseBody
	public String deleteProductById(@RequestParam("id") int id) {
		if (!productRepository.findById(id).isPresent()){
			return "Failure";
		}else {
			productRepository.deleteById(id);
			return "Product with id : " + id + " deleted";
		}
	}

	@GetMapping("/findProductByIdPage")
	public String findProductById(Model model){
		int id = 0;
		model.addAttribute("id", id);
		return "findProductByIdPage";
	}

	@PostMapping("/findProductByIdPage")
	@ResponseBody
	public String findProductById(@RequestParam("id") int id) {
		if (!productRepository.findById(id).isPresent()){
			return "Failure";
		}else {
			return productRepository.findById(id).get().toString();
		}
	}

	@GetMapping("/checkProductsOfCustomer{id}")
	@ResponseBody
	public String checkProductsOfCustomer(@PathVariable int id){
		return productService.findProductsOfCustomer(id);
	}

	@GetMapping("/checkCustomersOfProduct{id}")
	@ResponseBody
	public String checkCustomersOfProduct(@PathVariable int id){
		return productService.findCustomersOfProduct(id);
	}

	@GetMapping("/seeAllProductsPage/{id}")
	@ResponseBody
	public Optional<Product> getProduct(@PathVariable int id) {
		return productRepository.findById(id);
	}

	@GetMapping("/seeAllProductsPage")
	public String showAllProducts(Model model){
		model.addAttribute("productsList",productRepository.findAll());
		return "seeAllProductsPage";
	}

	@GetMapping("/addProductPage")
	public String addProduct(Model model){
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProductPage";
	}

	@PostMapping("/addProductPage")
	@ResponseBody
	public String addProduct(@ModelAttribute("product") Product product) {
		if (productRepository.findById(product.getId()).isPresent()){
			return "Failure";
		}
		productRepository.save(product);
		return product.toString() + " added sucessfully";
	}

}
