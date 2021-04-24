package com.goku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("VivBank API")
                .description("Administra a movimentacao bancaria do correntista e contrla o acesso através de CPF e senha")
                .license("")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("http://localhost:8085/vivbank/v1/suporte")
                .version("1.0.0")
                .contact(new Contact("","", "suporte@naotem.com"))
                .build();
    }

    @Bean
    public Docket customImplementation(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.goku"))
                .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}