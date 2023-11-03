package com.amx.conectio.evento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventoRepository extends JpaRepository<Evento, Long> {

//	@Query(value = "SELECT e FROM Evento e "
//			+ "WHERE FUNCTION('earth_distance', FUNCTION('ll_to_earth', e.latitud, e.longitud), FUNCTION('ll_to_earth', :latitud, :longitud)) < :maxDistancia "
//			+ "ORDER BY FUNCTION('earth_distance', FUNCTION('ll_to_earth', e.latitud, e.longitud), FUNCTION('ll_to_earth', :latitud, :longitud)) ASC")
//	List<Evento> findNearbyEventosOrderByDistance(@Param("latitud") double latitud,
//			@Param("longitud") double longitud, @Param("maxDistancia") double maxDistancia);

	@Query(value = "SELECT e FROM Evento e "
			+ "WHERE FUNCTION('earth_distance', FUNCTION('ll_to_earth', e.latitud, e.longitud), FUNCTION('ll_to_earth', :latitud, :longitud)) < :maxDistancia "
			+ "ORDER BY FUNCTION('earth_distance', FUNCTION('ll_to_earth', e.latitud, e.longitud), FUNCTION('ll_to_earth', :latitud, :longitud)) ASC")
	Page<Evento> findNearbyEventosOrderByDistance(@Param("latitud") double latitud, @Param("longitud") double longitud,
			@Param("maxDistancia") double maxDistancia, Pageable pageable);

//	@Query(value = "SELECT e FROM Evento e WHERE " +
//            "(6371 * acos(cos(radians(:latitud)) * cos(radians(e.latitud)) * cos(radians(e.longitud) - radians(:longitud)) + sin(radians(:latitud)) * sin(radians(e.latitud)))) < :maxDistancia " +
//            "ORDER BY (6371 * acos(cos(radians(:latitud)) * cos(radians(e.latitud)) * cos(radians(e.longitud) - radians(:longitud)) + sin(radians(:latitud)) * sin(radians(e.latitud)))) ASC")
//    Page<Evento> findNearbyEventosOrderByDistance(@Param("latitud") double latitud, @Param("longitud") double longitud, @Param("maxDistancia") double maxDistancia, Pageable pageable);

}
