package com.microservice.usuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.usuario.dto.UsuarioDto;
import com.microservice.usuario.entity.UsuarioEntity;
import com.microservice.usuario.repository.UsuarioRepository;
import com.microservice.usuarios.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void crearUsuario(UsuarioDto usuario) {
		usuarioRepository.crearUsuario(UsuarioEntity.parse(usuario));
	}

	@Override
	public List<UsuarioDto> verUsuarios() {
		List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
		for(UsuarioEntity usuario: usuarioRepository.verUsuarios()) {
			usuarios.add(UsuarioDto.parse(usuario));
		}
		return usuarios;
	}

	@Override
	public UsuarioDto buscarPorEmail(String email) {
		return UsuarioDto.parse(usuarioRepository.buscarPorEmail(email));
	}

}
