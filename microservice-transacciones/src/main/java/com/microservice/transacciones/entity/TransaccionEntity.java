package com.microservice.transacciones.entity;

import java.time.LocalDate;

import com.microservice.transacciones.dto.TransaccionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacciones")
public class TransaccionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String tipo;
	
	@Column
	private double cantidad;
	
	@Column
	private String categoria;
	
	@Column
	private String descripcion;
	
	@Column
	private LocalDate fecha_transaccion;
	
	@Column
	private Long usuario_id;
	
	public static TransaccionEntity parse(TransaccionDto transaccionDto) {
		TransaccionEntity nT = new TransaccionEntity();
		
		nT.setId(transaccionDto.getId());
		nT.setTipo(transaccionDto.getTipo());
		nT.setCantidad(transaccionDto.getCantidad());
		nT.setCategoria(transaccionDto.getCategoria());
		nT.setDescripcion(transaccionDto.getDescripcion());
		nT.setFecha_transaccion(transaccionDto.getFecha_transaccion());
		nT.setUsuario_id(transaccionDto.getUsuario_id());
		
		return nT;
		
		
	}
}
