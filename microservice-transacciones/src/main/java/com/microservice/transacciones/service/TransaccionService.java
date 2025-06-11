package com.microservice.transacciones.service;

import java.math.BigDecimal;
import java.util.List;

import com.microservice.transacciones.dto.TransaccionDto;

public interface TransaccionService {

	public void crearTransaccion(String token, TransaccionDto transaccionDto);
	
	public double obtenerTotalIngresosPorId(String token);
	
	public double obtenerTotalGastosPorId(String token);
	
	public BigDecimal obtenerBalancePorId(String token);
	
	public double obtenerTasaAhorroPorId(String token);
	
	public double obtenerIngresosMensualPorId(String token);
	
	public double obtenerGastosMensualPorId(String token);
	
	public BigDecimal obtenerBalanceMensualPorId(String token);
	
	public double obtenerTasaAhorroMensualPorId(String token);
	
	public double variacionIngresosMesAnteriorPorId(String token);
	
	public double variacionGastosMesAnteriorPorId(String token);
	
	public List<TransaccionDto> obtenerUltimasTransacciones(String token);
}
