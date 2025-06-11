package com.microservice.transacciones.repository;

import java.util.List;

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
	@Query(value = "INSERT INTO transacciones (tipo,cantidad,categoria,descripcion,fecha_transaccion,usuario_id) VALUES (:#{#transaccion.tipo},:#{#transaccion.cantidad},:#{#transaccion.categoria},:#{#transaccion.descripcion},:#{#transaccion.fecha_transaccion},:#{#transaccion.usuario_id})", nativeQuery = true)
	public void crearTransaccion(@Param("transaccion") TransaccionEntity transaccion);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'ingreso'", nativeQuery = true)
	public double obtenerTotalIngresosPorId(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'gasto'", nativeQuery = true)
	public double obtenerTotalGastosPorId(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'ingreso' AND MONTH(fecha_transaccion) = MONTH(CURDATE()) AND YEAR(fecha_transaccion) = YEAR(CURDATE())", nativeQuery = true)
	public double obtenerIngresosMensualPorId(@Param("usuario_id") Long usuario_id);
	
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'gasto' AND MONTH(fecha_transaccion) = MONTH(CURDATE()) AND YEAR(fecha_transaccion) = YEAR(CURDATE())", nativeQuery = true)
	public double obtenerGastosMensualPorId(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'ingreso' AND MONTH(fecha_transaccion) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(fecha_transaccion) = YEAR(CURDATE())", nativeQuery = true)
	public double obtenerIngresosMesAnteriorPorId(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT COALESCE(SUM(cantidad), 0) FROM transacciones WHERE usuario_id = :usuario_id AND tipo = 'gasto' AND MONTH(fecha_transaccion) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(fecha_transaccion) = YEAR(CURDATE())", nativeQuery = true)
	public double obtenerGastosMesAnteriorPorId(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT * FROM transacciones WHERE usuario_id = :usuario_id LIMIT 5", nativeQuery = true)
	public List<TransaccionEntity> obtenerUltimasTransacciones(@Param("usuario_id") Long usuario_id);
}
