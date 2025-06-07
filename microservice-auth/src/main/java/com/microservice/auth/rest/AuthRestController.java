package com.microservice.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.service.AuthService;
import com.microservice.auth.service.JwtService;

@RestController
@RequestMapping("/gpp/auth")
public class AuthRestController {
	
	@Autowired
	AuthService authService;
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<TokenResponse> registrarUsuario(@RequestBody RegisterRequest registerRequest){
		TokenResponse token = authService.registro(registerRequest);
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/loginUsuario")
	public ResponseEntity<TokenResponse> loginUsuario(@RequestBody LoginRequest loginRequest){
		TokenResponse token = authService.login(loginRequest);
		return ResponseEntity.ok(token);
	}
	
	@GetMapping("/obtenerUsuarioId")
	public ResponseEntity<Long> obtenerUsuarioId(@RequestHeader("Authorization") String token){
		Long usuarioId = jwtService.extractUserId(token);
		return ResponseEntity.ok(usuarioId);
	}
}	
