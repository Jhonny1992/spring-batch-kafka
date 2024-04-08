package pe.com.susaya.gateway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder
                .routes()
                .route( route ->route
                        .path("companies/company")
                        .uri("htttp://localhost:8081")
                )
                .build();
    }
}
