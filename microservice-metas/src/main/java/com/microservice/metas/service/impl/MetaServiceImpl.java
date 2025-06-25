package com.microservice.metas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.metas.client.MetaClient;
import com.microservice.metas.dto.MetaDto;
import com.microservice.metas.entity.MetaEntity;
import com.microservice.metas.repository.MetaRepository;
import com.microservice.metas.service.MetaService;

@Service
public class MetaServiceImpl implements MetaService{
	
	@Autowired
	MetaClient metaClient;
	
	@Autowired
	MetaRepository metaRepository;

	@Override
	public void crearMeta(String token, MetaDto meta) {
		meta.setUsuario_id(metaClient.obtenerUsuarioId(token));
		metaRepository.crearMeta(MetaEntity.parse(meta));
		
	}

}
