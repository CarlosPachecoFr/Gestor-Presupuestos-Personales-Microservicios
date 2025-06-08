package com.microservice.transacciones.service;

import com.microservice.transacciones.dto.TransaccionDto;

public interface TransaccionService {

	public void crearTransaccion(String token, TransaccionDto transaccionDto);
	
	public double obtenerTotalIngresosPorId(String token);
	
	public double obtenerTotalGastosPorId(String token);
	
	public double obtenerBalancePorId(String token);
	
	public double obtenerTasaAhorroPorId(String token);
}
