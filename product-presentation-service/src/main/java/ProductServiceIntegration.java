import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Collection<ProductDto> getAllProducts(){
        return restTemplate.getForObject("http://localhost:8090/products", Collection.class);
    }


}
