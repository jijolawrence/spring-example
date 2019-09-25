package com.jijo.module.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public Docket coreApi() {
        // return new Docket(DocumentationType.SWAGGER_2)
        // .groupName("module-api")
        // .apiInfo(apiInfo())
        // .select()
        // .paths(regex("/.*/service/.*"))
        // .build();

        return new Docket(DocumentationType.SWAGGER_2)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.jijo.module.service"))
                                                      .paths(PathSelectors.any())
                                                      .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                                   .title("Module REST with Swagger")
                                   .description("Module REST with Swagger")
                                   .termsOfServiceUrl("http://www.xyz.com")
                                   .version("1.0")
                                   .build();
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Swagger Configuraiton loaded..{webcontext\\swagger-ui.html}");
    }

}
