package iua.edu.ar;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		// @formatter:off
	    return new Docket(DocumentationType.SWAGGER_2)  
	  	      .select()                                  
	  	      .apis(RequestHandlerSelectors.any())   
	  	      //.paths(PathSelectors.any())     
	  	      .paths(PathSelectors.ant("/api/v1/**"))
	  	      .build()
	  	      .apiInfo(apiInfoMetadata());
		// @formatter:on

	}
	
	@SuppressWarnings("unchecked")
	private ApiInfo apiInfoMetadata() {
		// @formatter:off
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
	    	"API REST Final Ing Web 3",
	        "Esta es la API de Ingeniería Web 3, IUA",
	        "1.0",          
	        "Términos del servicio",
	        new Contact("CanoMaria", "https://github.com/CanoMaria", "mcano596@alumnos.iua.edu.ar"),
	        "Apache License Version 2.0",
	        "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
	     // @formatter:on
		return apiInfo;
	}
}
