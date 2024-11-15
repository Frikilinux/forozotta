package ar.zotta.forozotta.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("bearer-key", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .info(new Info()
            .version("1.0")
            .title("ForoHub | Oracle Next Education - Alura")
            .description(
                "API Rest ForoHub | Oracle Next Education - Alura")
            .contact(new Contact()
                .name("Zotta")
                .email("frikilinux@gmail.com"))
            .license(new License()
                .name("GPL 3.0")
                .url("https://www.gnu.org/licenses/gpl-3.0.en.html")));
  }

}
