package com.microservice.transacciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "microservice-auth")
public interface TransaccionClient {
	
	@GetMapping("/gpp/auth/obtenerUsuarioId")
	public Long obtenerUsuarioId(@RequestHeader("Authorization") String token);

}
