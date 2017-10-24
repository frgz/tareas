/**
 * 
 */
package com.frgz.tareas.lista.exception;

import com.frgz.tareas.core.exception.RecursoNoEncontradoException;

/**
 * @author fabio
 *
 */
public class ListaYaExisteException extends RecursoNoEncontradoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListaYaExisteException(String nombre) {
		super("La lista con nombre '" + nombre + "' ya existe.");
	}
}
