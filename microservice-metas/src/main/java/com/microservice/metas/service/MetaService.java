package com.microservice.metas.service;

import com.microservice.metas.dto.MetaDto;

public interface MetaService {

	public void crearMeta(String token, MetaDto meta);
}
