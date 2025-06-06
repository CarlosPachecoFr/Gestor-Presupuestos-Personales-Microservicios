package com.microservice.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.service.AuthService;

@RestController
@RequestMapping("/gpp/auth")
public class AuthRestController {
	
	@Autowired
	AuthService authService;
	
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
}	
