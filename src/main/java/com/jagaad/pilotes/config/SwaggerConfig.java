package com.jagaad.pilotes.config;


import com.jagaad.pilotes.controllers.ClientController;
import com.jagaad.pilotes.security.ClientDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class) // test
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.jagaad.pilotes"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }



    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Miquel Montoro API")
                .description("Place, find, or update Pilotes orders, the famous Majorcan recipe")
                .version("1.0.0")
                .contact(new Contact(
                        "Guilherme de Magalhaes Andrade",
                        null, //"github.com/guiuba"
                        "oxem@hotmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
