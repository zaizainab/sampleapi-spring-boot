package com.ecomindo.onboarding.sampleapi.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiEndPointsInfo()) 
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()));  
    }
    
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfo(
          "Onboarding REST API", 
          "Some custom description of API.", 
          "API TOS", 
          "Terms of service", 
          new Contact("Ecomindo", "www.ecomindo.com", "zainab.mahru@ecomindo.com"), 
          "License of API", "API license URL", Collections.emptyList());
    }
    
    private ApiKey apiKey() { 
        return new ApiKey("JWT", "Authorization", "header"); 
      }

      private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
      } 
      
      private List<SecurityReference> defaultAuth() { 
          AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
          AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
          authorizationScopes[0] = authorizationScope; 
          return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
      }
}
