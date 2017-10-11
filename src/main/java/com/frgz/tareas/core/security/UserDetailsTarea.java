/**
 * 
 */
package com.frgz.tareas.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.frgz.tareas.usuario.Usuario;

/**
 * @author fabio
 *
 */
public class UserDetailsTarea extends Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String AUTHORITY_GUEST = "ROLE_GUEST";

	private Usuario usuario;

	public UserDetailsTarea(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.usuario.getRole() == null) {
			return AuthorityUtils.createAuthorityList(AUTHORITY_GUEST);
		}
		return AuthorityUtils.commaSeparatedStringToAuthorityList(this.usuario.getRole().getCodigo());
	}

	@Override
	public String getUsername() {
		return this.usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
