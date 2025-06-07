package com.microservice.transacciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceTransaccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTransaccionesApplication.class, args);
	}

}
