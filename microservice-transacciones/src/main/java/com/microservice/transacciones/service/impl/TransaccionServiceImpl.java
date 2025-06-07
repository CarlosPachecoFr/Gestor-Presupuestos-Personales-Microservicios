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
		Long usuario_id = transaccionClient.obtenerUsuarioId(token);
		transaccionDto.setUsuario_id(usuario_id);
		transaccionRepository.crearTransaccion(TransaccionEntity.parse(transaccionDto));
	}

}
