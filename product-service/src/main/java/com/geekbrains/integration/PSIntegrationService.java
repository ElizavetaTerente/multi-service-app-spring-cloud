/*package com.geekbrains.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class PSIntegrationService {

    private final WebClient webClient;

    public Collection getAllProductsFromCore(){
        return webClient.get()
                .uri("/core/products")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceAccessException("from PS to core"))
                )
                .bodyToMono(Collection.class)
                .block();

        //если ответ не ожидается,то
        // . retrieve()
        //.toBodilessEntity()
        //.block();
    }
}

 */

