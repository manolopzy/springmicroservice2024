package com.worldexplorers.happylearning.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class HappyLearningGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyLearningGatewayApplication.class, args);
	}


//	@Autowired
//	private CorsWebFilter corsWebFilter;
//	
//	@PostConstruct
//	public void init() {
//		
//		System.out.println("cors config = " + corsWebFilter.toString());;
//	}
}
