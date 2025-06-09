package com.microservice.transacciones.dto;

import java.time.LocalDate;

import com.microservice.transacciones.entity.TransaccionEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDto {

	private Long id;
	private String tipo; //ingreso o gasto
	private double cantidad;
	private String categoria;
	private String descripcion;
	private LocalDate fecha_transaccion = LocalDate.now();
	private Long usuario_id;
	
	
	public TransaccionDto(String tipo, double cantidad, String categoria, String descripcion) {
		super();
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}
	
	public static TransaccionDto parse(TransaccionEntity transaccionEntity) {
		TransaccionDto nT = new TransaccionDto();
		
		nT.setId(transaccionEntity.getId());
		nT.setTipo(transaccionEntity.getTipo());
		nT.setCantidad(transaccionEntity.getCantidad());
		nT.setCategoria(transaccionEntity.getCategoria());
		nT.setDescripcion(transaccionEntity.getDescripcion());
		nT.setFecha_transaccion(transaccionEntity.getFecha_transaccion());
		nT.setUsuario_id(transaccionEntity.getUsuario_id());
		
		return nT;
	}
	
}
