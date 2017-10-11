/**
 * 
 */
package com.frgz.tareas.usuario.exception;

import com.frgz.tareas.core.exception.RecursoNoEncontradoException;

/**
 * @author fabio
 *
 */
public class UsuarioNoEncontradoException extends RecursoNoEncontradoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoException(String email) {
		super("El usuario con email '" + email + "' no existe.");
	}

}
