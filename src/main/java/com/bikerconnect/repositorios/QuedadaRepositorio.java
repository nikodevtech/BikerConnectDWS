package com.bikerconnect.repositorios;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikerconnect.entidades.Quedada;

/**
 * Interfaz que define un repositorio para la entidad {@link Quedada}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la tabla Quedada en la base de datos.
 */
public interface QuedadaRepositorio extends JpaRepository<Quedada, Long> {
	
	/**
	 * Obtiene todas las quedadas "Planificadas" cuya fecha y hora ya han pasado 
	 * de la actual para poder cambiarla a completada o cancelada
	 * @param estado el estado de la quedada "Planificada"
	 * @param fecha la fecha actual
	 * @return la lista de quedadas (con sus participantes) cuya fecha y hora ya pasaron de la actual
	 */
	@Query("SELECT q FROM Quedada q LEFT JOIN FETCH q.usuariosParticipantes WHERE q.estado = :estado AND q.fechaHoraEncuentro < :fechaActual")
    List<Quedada> buscarTodasQuedadasPendientesConParticipantes(@Param("estado") String estado, @Param("fechaActual") Calendar fechaActual);

}
