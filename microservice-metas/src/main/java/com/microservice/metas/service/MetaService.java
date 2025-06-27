package com.microservice.metas.service;

import java.util.List;

import com.microservice.metas.dto.MetaDto;

public interface MetaService {

	public void crearMeta(String token, MetaDto meta);
	
	public List<MetaDto> obtenerMetasUsuarioId(String token);
	
	public void añadirCantidadMeta(String token, double cantidad_añadir, Long id);
}
