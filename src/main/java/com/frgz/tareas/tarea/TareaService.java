package com.frgz.tareas.tarea;

import java.util.List;

/**
 * 
 * @author fabio
 *
 */
public interface TareaService {

	List<Tarea> visualizar(String nombre);

	void guardar(Tarea tarea);

	void eliminar(Long id);

	Tarea crear();

	void realizada(Long id);

	Tarea obtener(Long id);

}