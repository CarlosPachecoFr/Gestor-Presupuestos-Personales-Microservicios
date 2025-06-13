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
	
	@Query(value = "SELECT * FROM transacciones WHERE usuario_id = :usuario_id ORDER BY fecha_transaccion DESC, id DESC LIMIT 5", nativeQuery = true)
	public List<TransaccionEntity> obtenerUltimasTransacciones(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT CASE MONTH(fecha_transaccion) " +
            "WHEN 01 THEN 'Enero' " +
            "WHEN 02 THEN 'Febrero' " +
            "WHEN 03 THEN 'Marzo' " +
            "WHEN 04 THEN 'Abril' " +
            "WHEN 05 THEN 'Mayo' " +
            "WHEN 06 THEN 'Junio' " +
            "WHEN 07 THEN 'Julio' " +
            "WHEN 08 THEN 'Agosto' " +
            "WHEN 09 THEN 'Septiembre' " +
            "WHEN 10 THEN 'Octubre' " +
            "WHEN 11 THEN 'Noviembre' " +
            "WHEN 12 THEN 'Diciembre' END AS mes, " +
            "SUM(cantidad) AS total " +
            "FROM transacciones " +
            "WHERE fecha_transaccion >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) " +
            "AND tipo = 'ingreso' " +
            "AND usuario_id = :usuario_id " +
            "GROUP BY MONTH(fecha_transaccion) " +
            "ORDER BY MONTH(fecha_transaccion)",
    nativeQuery = true)
	public List<Object[]> obtenerIngresosUltimosMeses(@Param("usuario_id") Long usuario_id);
	
	@Query(value = "SELECT CASE MONTH(fecha_transaccion) " +
            "WHEN 01 THEN 'Enero' " +
            "WHEN 02 THEN 'Febrero' " +
            "WHEN 03 THEN 'Marzo' " +
            "WHEN 04 THEN 'Abril' " +
            "WHEN 05 THEN 'Mayo' " +
            "WHEN 06 THEN 'Junio' " +
            "WHEN 07 THEN 'Julio' " +
            "WHEN 08 THEN 'Agosto' " +
            "WHEN 09 THEN 'Septiembre' " +
            "WHEN 10 THEN 'Octubre' " +
            "WHEN 11 THEN 'Noviembre' " +
            "WHEN 12 THEN 'Diciembre' END AS mes, " +
            "SUM(cantidad) AS total " +
            "FROM transacciones " +
            "WHERE fecha_transaccion >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) " +
            "AND tipo = 'gasto' " +
            "AND usuario_id = :usuario_id " +
            "GROUP BY MONTH(fecha_transaccion) " +
            "ORDER BY MONTH(fecha_transaccion)",
    nativeQuery = true)
	public List<Object[]> obtenerGastosUltimosMeses(@Param("usuario_id") Long usuario_id);
	
	@Query(value = """
		    SELECT categoria, SUM(cantidad) AS total
    FROM transacciones
    WHERE tipo = 'gasto'
      AND fecha_transaccion >= DATE_FORMAT(CURDATE(), '%Y-%m-01')
      AND usuario_id = :usuario_id
    GROUP BY categoria
    ORDER BY total DESC
		""", nativeQuery = true)
	public List<Object[]> obtenerGastosPorCategoria(@Param("usuario_id") Long usuario_id);
}
