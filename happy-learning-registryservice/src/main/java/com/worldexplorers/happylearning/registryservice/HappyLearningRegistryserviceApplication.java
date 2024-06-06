package com.worldexplorers.happylearning.registryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class HappyLearningRegistryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyLearningRegistryserviceApplication.class, args);
	}

}
