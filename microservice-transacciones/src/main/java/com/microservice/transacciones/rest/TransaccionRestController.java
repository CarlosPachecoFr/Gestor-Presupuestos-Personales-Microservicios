package com.microservice.transacciones.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.transacciones.dto.TransaccionDto;
import com.microservice.transacciones.service.TransaccionService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/gpp/transaccion")
public class TransaccionRestController {

	@Autowired
	TransaccionService transaccionService;
	
	@PostMapping("/crearTransaccion")
	public void crearTransaccion(@RequestHeader("Authorization") String token, @RequestBody TransaccionDto transaccionDto) {
		transaccionService.crearTransaccion(token, transaccionDto);
	}
	
	@GetMapping("/obtenerTotalIngresosPorId")
	public double obtenerTotalIngresosPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerTotalIngresosPorId(token);
	}
	
	@GetMapping("/obtenerTotalGastosPorId")
	public double obtenerTotalGastosPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerTotalGastosPorId(token);
	}
	
	@GetMapping("/obtenerBalancePorId")
	public BigDecimal obtenerBalancePorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerBalancePorId(token);
	}
	
	@GetMapping("/obtenerTasaAhorroPorId")
	public double obtenerTasaAhorroPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerTasaAhorroPorId(token);
	}
	
	@GetMapping("/obtenerIngresosMensualPorId")
	public double obtenerIngresosMensualPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerIngresosMensualPorId(token);
	}
	
	@GetMapping("/obtenerGastosMensualPorId")
	public double obtenerGastosMensualPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerGastosMensualPorId(token);
	}
	
	@GetMapping("/obtenerBalanceMensualPorId")
	public BigDecimal obtenerBalanceMensualPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerBalanceMensualPorId(token);
	}
	
	@GetMapping("/obtenerTasaAhorroMensualPorId")
	public double obtenerTasaAhorroMensualPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.obtenerTasaAhorroMensualPorId(token);
	}
	
	@GetMapping("/variacionIngresosMesAnteriorPorId")
	public double variacionIngresosMesAnteriorPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.variacionIngresosMesAnteriorPorId(token);
	}
	
	@GetMapping("/variacionGastosMesAnteriorPorId")
	public double variacionGastosMesAnteriorPorId(@RequestHeader("Authorization") String token) {
		return transaccionService.variacionGastosMesAnteriorPorId(token);
	}
	
	@GetMapping("/obtenerUltimasTransacciones")
	public List<TransaccionDto> obtenerUltimasTransacciones(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerUltimasTransacciones(token);
	}
	
	@GetMapping("/obtenerIngresosUltimosMeses")
	public List<Object[]> obtenerIngresosUltimosMeses(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerIngresosUltimosMeses(token);
	}
	
	@GetMapping("/obtenerGastosUltimosMeses")
	public List<Object[]> obtenerGastosUltimosMeses(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerGastosUltimosMeses(token);
	}
	
	@GetMapping("/obtenerGastosPorCategoria")
	public List<Object[]> obtenerGastosPorCategoria(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerGastosPorCategoria(token);
	}
	
	@GetMapping("/obtenerIngresosPorCategoria")
	public List<Object[]> obtenerIngresosPorCategoria(@RequestHeader("Authorization") String token){
		return  transaccionService.obtenerIngresosPorCategoria(token);
	}
	
	@GetMapping("/obtenerTransacciones")
	public List<TransaccionDto> obtenerTransacciones(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerTransacciones(token);
	}
	
	@GetMapping("/obtenerTotalGastosPorCategoria")
	public List<Object[]> obtenerTotalGastosPorCategoria(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerTotalGastosPorCategoria(token);
	}
	
	@GetMapping("/obtenerTotalIngresosPorCategoria")
	public List<Object[]> obtenerTotalPorCategoria(@RequestHeader("Authorization") String token){
		return  transaccionService.obtenerTotalIngresosPorCategoria(token);
	}
	
	@GetMapping("/obtenerGastosSemanales")
	public List<Object[]> obtenerGastosSemanales(@RequestHeader("Authorization") String token){
		return transaccionService.obtenerGastosSemanales(token);
	}
	
	@GetMapping("/exportarArchivo")
	public void exportarArchivo(@RequestHeader("Authorization") String token, @RequestParam String periodo, @RequestParam String formato, HttpServletResponse response) throws IOException{
		if(formato.equals("excel")) {
			transaccionService.exportarCsv(token, periodo, formato, response);
		}
		if(formato.equals("json")) {
			transaccionService.exportarJSON(token, periodo, formato, response);
		}
		if(formato.equals("txt")) {
			transaccionService.exportarTxt(token, periodo, formato, response);
		}
	}
}
