package com.worldexplorers.happylearning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
//https://reflectoring.io/spring-cors/#:~:text=getAllBooks(type))%3B%20%7D-,Enabling%20CORS%20Configuration%20Globally%20in%20Spring%20Webflux,can%20be%20overridden%20as%20required.
@SpringBootTest
//@AutoConfigureWebTestClient
public class CorsTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void corsTest() {

//		WebTestClient webTestClient = WebTestClient.bindToController(new CrossOriginTestController()).build();
		ResponseSpec response = webTestClient.get()
                .uri("/cors-enabled-endpoint")
                .header("Origin", "http://any-origin.com")
                .exchange();
		response.expectHeader()
        .valueEquals("Access-Control-Allow-Origin", "*");
//        response.expectHeader()
//                .valueEquals("Access-Control-Allow-Origin", "*");
//		webTestClient.get().uri("http://hostname-ignored:666/cors-enabled-endpoint")
//				.header("Origin", "http://any-origin.com").exchange().expectHeader()
//				.valueEquals("Access-Control-Allow-Origin", "*");
	}
	
	
//	@Configuration(proxyBeanMethods = false)
//	public static class MyWebFluxConfigurer {
//
//	  @Bean
//	  public WebFluxConfigurer corsConfigurer() {
//	    return new WebFluxConfigurerComposite() {
//
//	      @Override
//	      public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
//	      }
//	    };
//	  }
//	}
}
