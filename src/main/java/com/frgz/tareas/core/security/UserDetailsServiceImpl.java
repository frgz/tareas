/**
 * 
 */
package com.frgz.tareas.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frgz.tareas.usuario.Usuario;
import com.frgz.tareas.usuario.UsuarioService;
import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

/**
 * @author fabio
 *
 */
@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsuarioService usuarioService;

	@Autowired
	public UserDetailsServiceImpl(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Usuario usuario = this.usuarioService.findByEmail(email);
			return new UserDetailsTarea(usuario);
		} catch (UsuarioNoEncontradoException e) {
			throw new UsernameNotFoundException("Usuario no existe con email: " + email);
		}
	}

}
