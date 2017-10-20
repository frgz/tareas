/**
 * 
 */
package com.frgz.tareas.tarea.exception;

import com.frgz.tareas.core.exception.RecursoNoEncontradoException;

/**
 * @author fabio
 *
 */
public class TareaNoEncontradaException extends RecursoNoEncontradoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TareaNoEncontradaException(Long id) {
		super("La tarea con id '" + id + "' no existe.");
	}

}
