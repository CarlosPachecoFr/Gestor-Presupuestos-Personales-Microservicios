package com.microservice.transacciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.transacciones.entity.TransaccionEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Long>{

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO transacciones (tipo,cantidad,categoria,descripcion,usuario_id) VALUES (:#{#transaccion.tipo},:#{#transaccion.cantidad},:#{#transaccion.categoria},:#{#transaccion.descripcion},:#{#transaccion.usuario_id})", nativeQuery = true)
	public void crearTransaccion(@Param("transaccion") TransaccionEntity transaccion);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'ingreso'", nativeQuery = true)
	public double obtenerTotalIngresosPorId(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'gasto'", nativeQuery = true)
	public double obtenerTotalGastosPorId(@Param("usuario_id") Long usuario_id);
}
