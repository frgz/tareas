/**
 * 
 */
package com.frgz.tareas.tarea;

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
interface TareaRepository extends JpaRepository<Tarea, Long> {

	@Query("select t from Tarea t where t.propietario.id = ?#{principal.idUsuario} and t.nombre = :nombre and t.lista.id = :idLista")
	List<Tarea> findByNombreContainingAndLista(@Param("nombre") String nombre, @Param("idLista") Long idLista);

}
