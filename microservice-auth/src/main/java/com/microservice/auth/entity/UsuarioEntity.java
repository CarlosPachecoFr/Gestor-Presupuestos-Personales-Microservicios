package com.microservice.auth.entity;

import com.microservice.auth.dto.UsuarioDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String email;
	
	@Column
	private String contraseña;
	
	
	public static UsuarioEntity parse(UsuarioDto usuario) {
		UsuarioEntity nUs = new UsuarioEntity();
		
		nUs.setId(usuario.getId());
		nUs.setNombre(usuario.getNombre());
		nUs.setApellido(usuario.getApellido());
		nUs.setEmail(usuario.getEmail());
		nUs.setContraseña(usuario.getContraseña());
		
		return nUs;
	}
}
