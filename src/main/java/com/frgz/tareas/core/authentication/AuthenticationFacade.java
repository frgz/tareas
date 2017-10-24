/**
 * 
 */
package com.frgz.tareas.core.authentication;

import org.springframework.security.core.Authentication;

import com.frgz.tareas.usuario.Usuario;

/**
 * @author fabio
 *
 */
public interface AuthenticationFacade {
	
	Authentication getAuthentication();

	Usuario getUsuario();

}
