package com.microservice.transacciones.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.transacciones.client.TransaccionClient;
import com.microservice.transacciones.dto.TransaccionDto;
import com.microservice.transacciones.entity.TransaccionEntity;
import com.microservice.transacciones.repository.TransaccionRepository;
import com.microservice.transacciones.service.TransaccionService;

@Service
public class TransaccionServiceImpl implements TransaccionService{
	
	@Autowired
	TransaccionRepository transaccionRepository;
	@Autowired
	TransaccionClient transaccionClient;

	@Override
	public void crearTransaccion(String token, TransaccionDto transaccionDto) {
		transaccionDto.setUsuario_id(obtenerIdUsuarioToken(token));
		transaccionRepository.crearTransaccion(TransaccionEntity.parse(transaccionDto));
	}

	@Override
	public double obtenerTotalIngresosPorId(String token) {
		return transaccionRepository.obtenerTotalIngresosPorId(obtenerIdUsuarioToken(token));
	}

	@Override
	public double obtenerTotalGastosPorId(String token) {
		return transaccionRepository.obtenerTotalGastosPorId(obtenerIdUsuarioToken(token));
	}
	
	@Override
	public double obtenerBalancePorId(String token) {
		double ingresos = transaccionRepository.obtenerTotalIngresosPorId(obtenerIdUsuarioToken(token));
		double gastos = transaccionRepository.obtenerTotalGastosPorId(obtenerIdUsuarioToken(token));
		double balance = ingresos - gastos;
		return balance;
	}
	
	@Override
	public double obtenerTasaAhorroPorId(String token) {
		double ingresos = transaccionRepository.obtenerTotalIngresosPorId(obtenerIdUsuarioToken(token));
		double gastos = transaccionRepository.obtenerTotalGastosPorId(obtenerIdUsuarioToken(token));
		double tasaAhorro = (gastos/ingresos)*100;
		return tasaAhorro;
	}
	
	private Long obtenerIdUsuarioToken(String token) {
		return  transaccionClient.obtenerUsuarioId(token);
	}

	

}
