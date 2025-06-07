package com.microservice.transacciones.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.transacciones.dto.TransaccionDto;
import com.microservice.transacciones.service.TransaccionService;

@RestController
@RequestMapping("/gpp/transaccion")
public class TransaccionRestController {

	@Autowired
	TransaccionService transaccionService;
	
	@PostMapping("/crearTransaccion")
	public void crearTransaccion(@RequestHeader("Authorization") String token, @RequestBody TransaccionDto transaccionDto) {
		transaccionService.crearTransaccion(token, transaccionDto);
	}
}
