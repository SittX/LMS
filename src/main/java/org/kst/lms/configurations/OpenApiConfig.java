package org.kst.lms.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    private static final List<Server> servers = List.of(
            new Server().url("http://localhost:8080").description("DEV"),
            new Server().url("http://192.168.1.1:8080").description("UAT")
    );
    private final String schemeName = "bearerAuth";
    private final String bearerFormat = "JWT";
    private final String scheme = "bearer";

    @Bean
    public OpenAPI createOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(new Components().addSecuritySchemes(schemeName,
                                new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat(bearerFormat)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme(scheme)
                        )
                )
                .info(
                        new Info()
                                .title("Open API Specifications : LMS")
                                .description("Open API Specifications : LMS")
                                .version("1.0.0")
                )
                .servers(servers);
    }
}
