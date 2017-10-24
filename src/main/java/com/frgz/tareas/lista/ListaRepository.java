/**
 * 
 */
package com.frgz.tareas.lista;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author fabio
 *
 */
@Repository
interface ListaRepository extends JpaRepository<Lista, Long> {

	@Query("select l from Lista l where l.propietario.id = ?#{principal.idUsuario} and l.nombre like %:nombre%")
	List<Lista> findByNombreContaining(@Param("nombre") String nombre);

	@Query("select count(l) from Lista l where l.propietario.id = ?#{principal.idUsuario} and l.nombre = :nombre")
	int countByNombre(@Param("nombre") String nombre);

	@Query("select count(l) from Lista l where l.propietario.id = ?#{principal.idUsuario} and l.nombre = :nombre and l.id <> :idLista")
	int countByNombreAndDistinctLista(@Param("nombre") String nombre, @Param("idLista") Long idLista);
}
