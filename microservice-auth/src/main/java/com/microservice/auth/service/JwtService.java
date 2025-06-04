package com.microservice.auth.service;

import com.microservice.auth.entity.UsuarioEntity;

public interface JwtService {
	
	public String generarToken(UsuarioEntity usuario);
	
	public String tokenBuilder(UsuarioEntity usuario, long jwtExpiration);
	
	public String extractUsername(String token);
}
