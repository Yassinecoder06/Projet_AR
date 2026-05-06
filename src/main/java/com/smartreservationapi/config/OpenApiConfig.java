package com.smartreservationapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Smart Reservation API",
                version = "v1",
                description = "Reservation API with conflict detection"
        )
)
public class OpenApiConfig {
}
