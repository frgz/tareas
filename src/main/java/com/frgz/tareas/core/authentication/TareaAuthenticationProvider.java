/**
 * 
 */
package com.frgz.tareas.core.authentication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.frgz.tareas.core.authentication.exeption.TareaAuthenticationException;
import com.frgz.tareas.core.security.UserDetailsTarea;
import com.frgz.tareas.usuario.Usuario;
import com.frgz.tareas.usuario.UsuarioService;
import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

/**
 * @author fabio
 *
 */
@Component
public class TareaAuthenticationProvider implements AuthenticationProvider {

	private UsuarioService usuarioService;

	@Autowired
	public TareaAuthenticationProvider(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = (String) authentication.getPrincipal();
		try {
			Usuario usuario = this.usuarioService.findByEmail(email);
			if (!usuario.isActivo()) {
				throw new TareaAuthenticationException("Usuario no activo con email: " + email);
			}
			BCryptPasswordEncoder passwordEncoder = passwordEncoder();
			if (!passwordEncoder.matches((String) authentication.getCredentials(), usuario.getPassword())) {
				throw new TareaAuthenticationException("Credenciales incorrectas para el usuario con email: " + email);
			}
			usuario.setFechaUltimoAcceso(Calendar.getInstance().getTime());

			return new UsernamePasswordAuthenticationToken(new UserDetailsTarea(usuario),
					authentication.getCredentials(), getAuthorities(usuario));
		} catch (UsuarioNoEncontradoException e) {
			throw new TareaAuthenticationException("Usuario no encontrado co email: " + email, e);
		}
	}

	private final List<GrantedAuthority> getAuthorities(final Usuario usuario) {
		final List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		authoritiesList.add(new SimpleGrantedAuthority(usuario.getRole().getCodigo()));
		return authoritiesList;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
