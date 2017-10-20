/**
 * 
 */
package com.frgz.tareas.lista.exception;

import com.frgz.tareas.core.exception.RecursoNoEncontradoException;

/**
 * @author fabio
 *
 */
public class ListaNoEncontradaException extends RecursoNoEncontradoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListaNoEncontradaException(Long id) {
		super("La lista con id '" + id + "' no existe.");
	}
}
