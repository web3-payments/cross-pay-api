package com.cross.chain.payment.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("payments")
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.cross.chain.payment.controller"))
                    .build()
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Swagger CrossPay *  - OpenAPI 3.0 - Name to be defined")
            .description("Description goes here...   _also here_  Some useful links: - [Some link](https://github.com/)")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.11")
            .contact(new Contact("","", "crosspayteam@crosspay.com"))
            .build();
    }

//    @Bean
//    public OpenAPI openApi() {
//        return new OpenAPI()
//            .info(new Info()
//                .title("Swagger CrossPay *  - OpenAPI 3.0 - Name to be defined")
//                .description("Description goes here...   _also here_  Some useful links: - [Some link](https://github.com/)")
//                .termsOfService("")
//                .version("1.0.11")
//                .license(new License()
//                    .name("Apache 2.0")
//                    .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
//                .contact(new io.swagger.v3.oas.models.info.Contact()
//                    .email("crosspayteam@crosspay.com")));
//    }

}
