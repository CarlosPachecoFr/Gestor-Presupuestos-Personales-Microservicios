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
		double tasaAhorro = (gastos / ingresos) * 100;
		return tasaAhorro;
	}
	
	private Long obtenerIdUsuarioToken(String token) {
		return  transaccionClient.obtenerUsuarioId(token);
	}

	@Override
	public double obtenerIngresosMensualPorId(String token) {
		return transaccionRepository.obtenerIngresosMensualPorId(transaccionClient.obtenerUsuarioId(token));
	}

	@Override
	public double obtenerGastosMensualPorId(String token) {
		return transaccionRepository.obtenerGastosMensualPorId(transaccionClient.obtenerUsuarioId(token));
	}

	@Override
	public double obtenerBalanceMensualPorId(String token) {
		double ingresos = transaccionRepository.obtenerIngresosMensualPorId(obtenerIdUsuarioToken(token));
		double gastos = transaccionRepository.obtenerGastosMensualPorId(obtenerIdUsuarioToken(token));
		double balance = ingresos - gastos;
		return balance;
	}

	@Override
	public double obtenerTasaAhorroMensualPorId(String token) {
		double ingresos = transaccionRepository.obtenerIngresosMensualPorId(obtenerIdUsuarioToken(token));
		double gastos = transaccionRepository.obtenerGastosMensualPorId(obtenerIdUsuarioToken(token));
		double tasaAhorro = (gastos / ingresos) * 100;
		return tasaAhorro;
	}

	@Override
	public double variacionIngresosMesAnteriorPorId(String token) {
		double ingresosMesActual = transaccionRepository.obtenerIngresosMensualPorId(transaccionClient.obtenerUsuarioId(token));
		double ingresosMesAnterior = transaccionRepository.obtenerIngresosMesAnteriorPorId(transaccionClient.obtenerUsuarioId(token));
		double variacion = ((ingresosMesActual - ingresosMesAnterior) / ingresosMesAnterior) * 100;
		return variacion;
	}

	@Override
	public double variacionGastosMesAnteriorPorId(String token) {
		double gastosMesActual = transaccionRepository.obtenerGastosMensualPorId(transaccionClient.obtenerUsuarioId(token));
		double gastosMesAnterior = transaccionRepository.obtenerGastosMesAnteriorPorId(transaccionClient.obtenerUsuarioId(token));
		double variacion = ((gastosMesActual - gastosMesAnterior) / gastosMesAnterior) * 100;
		return variacion;
	}

	

}
