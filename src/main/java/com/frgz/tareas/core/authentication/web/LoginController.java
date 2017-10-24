/**
 * 
 */
package com.frgz.tareas.core.authentication.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fabio
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String LOGIN_PAGE = "login";
	private static final String HOME_PAGE = "redirect:/lista";

	@GetMapping
	public String login(Principal principal) {
		return principal == null ? LOGIN_PAGE : HOME_PAGE;
	}

}
