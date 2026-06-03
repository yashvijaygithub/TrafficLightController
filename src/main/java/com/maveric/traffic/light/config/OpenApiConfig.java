package com.maveric.traffic.light.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI trafficLightOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Traffic Light Control API")
                        .version("1.0")
                        .description(
                                "API for managing traffic lights, sequences, pause/resume operations, and signal history.")
                        .contact(new Contact()
                                .name("Traffic Operations Team")
                                .email("support@trafficlight.com")));
    }
}
