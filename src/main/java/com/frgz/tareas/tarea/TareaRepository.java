/**
 * 
 */
package com.frgz.tareas.tarea;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabio
 *
 */
@Repository
interface TareaRepository extends JpaRepository<Tarea, Long> {
	
	List<Tarea> findByNombreContaining(String nombre);

}
