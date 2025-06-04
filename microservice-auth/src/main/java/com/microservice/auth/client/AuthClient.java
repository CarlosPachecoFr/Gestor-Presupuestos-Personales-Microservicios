package com.microservice.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.auth.dto.UsuarioDto;
import com.microservice.auth.entity.UsuarioEntity;

@FeignClient(name = "msvc-usuario")
public interface AuthClient {

	@PostMapping("/gpp/usuario/crearUsuario")
	public void crearUsuario(@RequestBody UsuarioDto usuario);
	
	@GetMapping("/gpp/usuario/buscarPorEmail")
	public UsuarioDto buscarPorEmail(@RequestParam String email);
}
