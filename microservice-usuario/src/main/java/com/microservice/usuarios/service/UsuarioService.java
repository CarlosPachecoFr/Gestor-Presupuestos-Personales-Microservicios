package com.microservice.usuarios.service;

import java.util.List;

import com.microservice.usuario.dto.UsuarioDto;
import com.microservice.usuario.entity.UsuarioEntity;

public interface UsuarioService {

	public void crearUsuario(UsuarioDto usuario);
	
	public List<UsuarioDto> verUsuarios();
	
	public UsuarioDto buscarPorEmail(String email);
}
