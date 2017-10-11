/**
 * 
 */
package com.frgz.tareas.core.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author fabio
 *
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	private static final String GUEST = "guest";

	@Override
	public String getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication == null ? GUEST : authentication.getName();
	}

}
