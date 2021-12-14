package br.com.dh.config.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    	      .apiInfo(apiInfo())
	    	      .select()
	    	      .apis(RequestHandlerSelectors.basePackage("br.com.dh"))
	    	      .paths(PathSelectors.any())
	    	      .build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Gerenciador Financeiro",
			"API para gerenciador financeiro pessoal que permite cadastrar receitas e despesas",
			"1.0",
			"Terms of service",
			new Contact("Juliana Aquino", "www.juliana.com", "juliana@email.com"),
			"License of API",
			"API license URL",
			Collections.emptyList());
	}

}