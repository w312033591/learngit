package com.example.springcloudproducer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudProducer2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudProducer2Application.class, args);
	}
}
