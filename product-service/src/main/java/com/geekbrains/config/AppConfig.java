/*package com.geekbrains.config;

import com.geekbrains.properties.CoreServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(CoreServiceIntegrationProperties.class)

public class AppConfig {
    @Autowired
    CoreServiceIntegrationProperties coreServiceIntegrationProperties;

        @Bean
    public WebClient productServiceWebClient(){
            TcpClient tcpClient = TcpClient
                    .create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,coreServiceIntegrationProperties.getConnectionTimeout())
                    .doOnConnected(connection -> {
                        connection.addHandlerLast(new ReadTimeoutHandler(coreServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                        connection.addHandlerLast(new WriteTimeoutHandler(coreServiceIntegrationProperties.getWriteTimeout(),TimeUnit.MILLISECONDS));
                    });
            return WebClient
                    .builder()
                    .baseUrl(coreServiceIntegrationProperties.getUrl())
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .build();
        }
}

 */

