/*package com.geekbrains.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CSIntegrationService {

    private final WebClient webClient;

    public String getCurrentlyLoggedInUserFromAuthService(){
        return webClient.get()
                .uri("/auth/getCurrentlyLoggedItUser")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceAccessException("from Cart to Auth"))
                )
                .bodyToMono(String.class)
                .block();

        //если ответ не ожидается,то
        // . retrieve()
        //.toBodilessEntity()
        //.block();
    }
}
 */
