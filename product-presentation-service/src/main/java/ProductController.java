import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
public class ProductController {

	@Autowired
	ProductServiceIntegration productServiceIntegration;

	@GetMapping("/products") //ЗАПРОС ДОЛЖЕН ПРИЙТИ СЮДА НО Я ПОЛУЧАЮ 404
	public Collection<ProductDto> allProductsList(){
		System.out.println("da");
		return productServiceIntegration.getAllProducts();
	}

/*
	@PostMapping("/products/addNew/{title}/{cost}")
	public void addNewProduct(@PathVariable String title, @PathVariable double cost){
			productService.saveOrUpdate(title, cost);
	}

	@DeleteMapping("/products/delete/{title}")
	public void deleteProduct(@PathVariable String title){
		productService.delete(title);
	}

 */



}
