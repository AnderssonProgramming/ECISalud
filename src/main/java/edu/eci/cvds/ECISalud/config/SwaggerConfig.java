
package edu.eci.cvds.ECISalud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecisaludOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ECI Salud Vital API")
                        .description("API for managing medical appointments at ECI Salud Vital")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("ECI Salud Vital")
                                .url("https://god-fwcafqgvhvbdfthh.canadacentral-01.azurewebsites.net")
                                .email("contact@ecisalud.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}