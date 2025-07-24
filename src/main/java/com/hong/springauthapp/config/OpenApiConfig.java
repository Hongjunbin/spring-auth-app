package com.hong.springauthapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "spring-auth-app",
                version = "version-1",
                description = "spring-auth-app API docs"
        ),
        servers = {
                @Server(url = "http://localhost:8888/", description = "local environment (http)")
        }
)
@SecurityScheme(
        name = "JWT Token - Authorization: Bearer {value} ",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
