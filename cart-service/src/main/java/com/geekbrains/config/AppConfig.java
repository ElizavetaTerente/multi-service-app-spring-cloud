/*package com.geekbrains.config;

import com.geekbrains.properties.AuthServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(AuthServiceIntegrationProperties.class)

public class AppConfig {
    @Autowired
    AuthServiceIntegrationProperties authServiceIntegrationProperties;

        @Bean
    public WebClient productServiceWebClient(){
            TcpClient tcpClient = TcpClient
                    .create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, authServiceIntegrationProperties.getConnectionTimeout())
                    .doOnConnected(connection -> {
                        connection.addHandlerLast(new ReadTimeoutHandler(authServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                        connection.addHandlerLast(new WriteTimeoutHandler(authServiceIntegrationProperties.getWriteTimeout(),TimeUnit.MILLISECONDS));
                    });
            return WebClient
                    .builder()
                    .baseUrl(authServiceIntegrationProperties.getUrl())
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .build();
        }
}

 */
