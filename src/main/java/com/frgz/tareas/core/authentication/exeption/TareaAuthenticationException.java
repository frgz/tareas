/**
 * 
 */
package com.frgz.tareas.core.authentication.exeption;

import org.springframework.security.core.AuthenticationException;

/**
 * @author fdieper
 *
 */
public class TareaAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TareaAuthenticationException(String msg) {
		super("Error en la autenticación: " + msg);
	}

	public TareaAuthenticationException(String msg, Throwable t) {
		super("Error en la autenticación: " + msg, t);
	}

}
