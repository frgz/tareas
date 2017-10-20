package com.frgz.tareas.tarea;

import java.util.List;

import com.frgz.tareas.lista.exception.ListaNoEncontradaException;
import com.frgz.tareas.tarea.exception.TareaNoEncontradaException;

/**
 * 
 * @author fabio
 *
 */
public interface TareaService {

	List<Tarea> visualizar(String nombre);

	void guardar(Tarea tarea);

	void eliminar(Long id);

	void realizada(Long id);

	Tarea obtener(Long id) throws TareaNoEncontradaException;

	Tarea crear(Long idLista) throws ListaNoEncontradaException;

}