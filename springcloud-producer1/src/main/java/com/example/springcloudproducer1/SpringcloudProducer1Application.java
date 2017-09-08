package com.example.springcloudproducer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudProducer1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudProducer1Application.class, args);
	}
}
