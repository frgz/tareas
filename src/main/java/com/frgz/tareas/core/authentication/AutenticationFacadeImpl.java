/**
 * 
 */
package com.frgz.tareas.core.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.frgz.tareas.core.security.UserDetailsTarea;
import com.frgz.tareas.usuario.Usuario;

/**
 * @author fabio
 *
 */
@Component
class AutenticationFacadeImpl implements AuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public Usuario getUsuario() {
		Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = new Usuario();
		usuario.setId(((UserDetailsTarea) autenticacion.getPrincipal()).getIdUsuario());
		return usuario;
	}

}
