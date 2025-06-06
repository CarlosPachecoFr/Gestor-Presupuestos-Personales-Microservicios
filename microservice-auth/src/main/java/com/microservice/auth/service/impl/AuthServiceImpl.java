package com.microservice.auth.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.auth.client.AuthClient;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.dto.UsuarioDto;
import com.microservice.auth.entity.UsuarioEntity;
import com.microservice.auth.rest.TokenResponse;
import com.microservice.auth.service.AuthService;
import com.microservice.auth.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final AuthClient authClient;

	@Override
	public TokenResponse registro(RegisterRequest request) {
		UsuarioDto usuario = new UsuarioDto(
				request.getNombre(),
				request.getApellido(),
				request.getEmail(),
				passwordEncoder.encode(request.getContraseña())
				);
		
		UsuarioDto usuarioExistente = authClient.buscarPorEmail(usuario.getEmail());
		if(usuarioExistente != null) {
			throw new RuntimeException("Usuario ya existente");
		}
		
		authClient.crearUsuario(usuario);
		UsuarioEntity usuarioCreado = UsuarioEntity.parse(authClient.buscarPorEmail(usuario.getEmail()));
		String jwtToken = jwtService.generarToken(usuarioCreado);
		return new TokenResponse(jwtToken);
	}

	@Override
	public TokenResponse login(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContraseña())
				);
		UsuarioEntity usuario = UsuarioEntity.parse(authClient.buscarPorEmail(request.getEmail()));
		
		if (usuario == null) {
	        throw new RuntimeException("Usuario no encontrado");
	    }
		
		String jwtToken = jwtService.generarToken(usuario);
		return new TokenResponse(jwtToken);
	}

	
}
