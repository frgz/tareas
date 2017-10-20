/**
 * 
 */
package com.frgz.tareas.lista;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabio
 *
 */
@Repository
interface ListaRepository extends JpaRepository<Lista, Long> {

	List<Lista> findByNombreContaining(String nombre);

}
