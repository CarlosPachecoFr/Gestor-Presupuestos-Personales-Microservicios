package com.microservice.usuario.dto;

import com.microservice.usuario.entity.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String contraseña;
	
	public UsuarioDto(String nombre, String apellido, String email, String contraseña) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
	}
	
	public static UsuarioDto parse(UsuarioEntity usuario) {
		UsuarioDto nUs = new UsuarioDto();
		
		nUs.setId(usuario.getId());
		nUs.setNombre(usuario.getNombre());
		nUs.setApellido(usuario.getApellido());
		nUs.setEmail(usuario.getEmail());
		nUs.setContraseña(usuario.getContraseña());
		
		return nUs;
	}
	
}
