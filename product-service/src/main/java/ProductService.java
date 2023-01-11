import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductDto mappingFromProductToProductDto(Product product){
       return new ProductDto(product.getTitle(),product.getCost());
    }

    public Collection<ProductDto> mappingFromProductCollectionToProductDtoCollection(Collection<Product> products){
        Collection<ProductDto> productDtos = new ArrayList<>();
        for (Product product:products){
            productDtos.add(new ProductDto(product.getTitle(),product.getCost()));
        }
        return productDtos;
    }

 //   @LogExecutionTime
    public Collection<ProductDto> getAllProducts(){

        return mappingFromProductCollectionToProductDtoCollection(productRepository.findAll());
    }
    /*

 //   @LogExecutionTime
    public void saveOrUpdate(String title,double cost) {
        if(cost <= 0){throw new AddNewProductException("price cannot be less than or equals 0");
        }else if(productRepository.existsById(title)){
            throw new AddNewProductException("product with such a name exists already");
        }
        productRepository.save(new Product(title, cost));
    }
 //   @LogExecutionTime
    public void delete(String title){
        productRepository.deleteById(title);
    }

  //  @LogExecutionTime
    public Product findByName(String title){
        Optional<Product> product = productRepository.findById(title);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new IllegalArgumentException("product doesnt exist!");
        }
    }

     */
}
