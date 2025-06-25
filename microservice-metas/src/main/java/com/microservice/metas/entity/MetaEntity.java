package com.microservice.metas.entity;

import java.time.LocalDate;

import com.microservice.metas.dto.MetaDto;

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
@Table(name = "metas")
public class MetaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private double cantidad_actual;
	
	@Column
	private double cantidad_objetivo;
	
	@Column
	private LocalDate fecha_finalizacion;
	
	@Column
	private Long usuario_id;
	
	public static MetaEntity parse(MetaDto meta) {
		MetaEntity nMeta = new MetaEntity();
		
		nMeta.setId(meta.getId());
		nMeta.setNombre(meta.getNombre());
		nMeta.setCantidad_actual(meta.getCantidad_actual());
		nMeta.setCantidad_objetivo(meta.getCantidad_objetivo());
		nMeta.setFecha_finalizacion(meta.getFecha_finalizacion());
		nMeta.setUsuario_id(meta.getUsuario_id());
		
		return nMeta;
	}
}
