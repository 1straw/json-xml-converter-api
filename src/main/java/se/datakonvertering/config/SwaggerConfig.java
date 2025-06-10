package se.datakonvertering.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("XML/JSON converter")
                .version("1.0.0") // Hårdkodad version
                .description("This is a simple server for converting between XML and JSON")
                .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org")));
    }

}
