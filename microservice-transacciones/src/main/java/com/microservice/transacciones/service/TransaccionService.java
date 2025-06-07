package com.microservice.transacciones.service;

import com.microservice.transacciones.dto.TransaccionDto;

public interface TransaccionService {

	public void crearTransaccion(String token, TransaccionDto transaccionDto);
}
