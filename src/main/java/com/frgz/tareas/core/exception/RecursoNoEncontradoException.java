/**
 * 
 */
package com.frgz.tareas.core.exception;

/**
 * @author fdieper
 *
 */
public class RecursoNoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoNoEncontradoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecursoNoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecursoNoEncontradoException(String message) {
		super(message);
	}

	public RecursoNoEncontradoException(Throwable cause) {
		super(cause);
	}

}
