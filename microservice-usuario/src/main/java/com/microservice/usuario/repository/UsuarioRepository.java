package com.microservice.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.usuario.entity.UsuarioEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO usuarios (nombre,apellido,email,contraseña) VALUES (:#{#usuario.nombre}, :#{#usuario.apellido}, :#{#usuario.email}, :#{#usuario.contraseña})", nativeQuery = true)
	void crearUsuario(@Param("usuario") UsuarioEntity usuario);
	
	@Query(value = "SELECT * FROM usuarios", nativeQuery = true)
	List<UsuarioEntity> verUsuarios();
	
	@Query(value = "SELECT * FROM usuarios WHERE email = :email", nativeQuery = true)
	public UsuarioEntity buscarPorEmail(@Param("email") String email);
}
