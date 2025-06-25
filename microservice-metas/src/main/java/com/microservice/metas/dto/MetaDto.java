package com.microservice.metas.dto;

import java.time.LocalDate;

import com.microservice.metas.entity.MetaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaDto {
	private Long id;
	private String nombre;
	private double cantidad_actual;
	private double cantidad_objetivo;
	private LocalDate fecha_finalizacion;
	private Long usuario_id;
	
	public MetaDto(String nombre, double cantidad_objetivo, LocalDate fecha_finalizacion) {
		super();
		this.nombre = nombre;
		this.cantidad_objetivo = cantidad_objetivo;
		this.fecha_finalizacion = fecha_finalizacion;
		this.cantidad_actual = 0;
	}
	
	public static MetaDto parse(MetaEntity meta) {
		MetaDto nMeta = new MetaDto();
		
		nMeta.setId(meta.getId());
		nMeta.setNombre(meta.getNombre());
		nMeta.setCantidad_actual(meta.getCantidad_actual());
		nMeta.setCantidad_objetivo(meta.getCantidad_objetivo());
		nMeta.setFecha_finalizacion(meta.getFecha_finalizacion());
		nMeta.setUsuario_id(meta.getUsuario_id());
		
		return nMeta;
	}
}
