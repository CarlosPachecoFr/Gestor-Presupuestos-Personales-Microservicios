package com.microservice.metas.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<MetaDto> obtenerMetasUsuarioId(String token) {
		List<MetaDto> metas = new ArrayList<>();
		for(MetaEntity meta: metaRepository.obtenerMetasUsuarioId(metaClient.obtenerUsuarioId(token))) {
			metas.add(MetaDto.parse(meta));
		}
		return metas;
	}

	@Override
	public void añadirCantidadMeta(String token, double cantidad_añadir, Long id) {
		Long usuario_id = metaClient.obtenerUsuarioId(token);
		metaRepository.añadirCantidadMeta(usuario_id, cantidad_añadir, id);
	}

	@Override
	public MetaDto obtenerMetaPorId(Long id) {
		return MetaDto.parse(metaRepository.obtenerMetaPorId(id));
	}

}
