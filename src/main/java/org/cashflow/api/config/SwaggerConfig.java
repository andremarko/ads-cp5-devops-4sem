package org.cashflow.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.cashflow.api.controller", "org.cashflow.api.dto"})
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Cash Flow Restful API")
                        .version("v1")
                        .description("API para cadastro e gerenciamento de fluxo de caixa. Cada fluxo está relacionado a um cliente")
                        .contact(new Contact()
                                .name("Suporte API")));
    }

}
