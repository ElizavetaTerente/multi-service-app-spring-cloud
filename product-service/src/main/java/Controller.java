import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin("*")
public class Controller {

    @Autowired
    ProductService productService;

    @GetMapping("http://localhost:8090/products")
    public Collection<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

}
