package com.microservice.metas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.metas.entity.MetaEntity;

import jakarta.transaction.Transactional;

@Repository
public interface MetaRepository extends JpaRepository<MetaEntity, Long>{

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO metas (nombre, cantidad_actual, cantidad_objetivo, fecha_finalizacion, usuario_id) VALUES (:#{#meta.nombre}, :#{#meta.cantidad_actual}, :#{#meta.cantidad_objetivo}, :#{#meta.fecha_finalizacion}, :#{#meta.usuario_id})", nativeQuery = true)
	public void crearMeta(@Param("meta") MetaEntity meta);
	
	@Query( value = "SELECT * FROM metas WHERE usuario_id = :usuario_id", nativeQuery = true)
	public List<MetaEntity> obtenerMetasUsuarioId(@Param("usuario_id") Long usuario_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE metas SET cantidad_actual = cantidad_actual + :cantidad_añadir WHERE usuario_id = :usuario_id AND id = :id", nativeQuery = true)
	public void añadirCantidadMeta(@Param("usuario_id") Long usuario_id, @Param("cantidad_añadir") double cantidad_añadir, @Param("id") Long id);
}
