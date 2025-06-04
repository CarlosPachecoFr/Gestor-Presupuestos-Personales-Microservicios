package com.microservice.usuario.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.usuario.dto.UsuarioDto;
import com.microservice.usuario.entity.UsuarioEntity;
import com.microservice.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/gpp/usuario")
public class UsuarioRestController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/crearUsuario")
	public void crearUsuario(@RequestBody UsuarioDto usuario) {
		usuarioService.crearUsuario(usuario);
	}
	
	@GetMapping("/verUsuarios")
	public List<UsuarioDto> verUsuarios(){
		return usuarioService.verUsuarios();
	}
	
	@GetMapping("/buscarPorEmail")
	public UsuarioDto buscarPorEmail(@RequestParam String email) {
		return usuarioService.buscarPorEmail(email);
	}
}
