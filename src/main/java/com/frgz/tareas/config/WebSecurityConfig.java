/**
 * 
 */
package com.frgz.tareas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author fabio
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    	
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Configuracion de seguridad
		httpSecurity.authorizeRequests()
		   .antMatchers("/webjars/**", "/resources/**").permitAll()
		   .antMatchers("/tarea").access("hasRole('ROLE_ADMIN')")
		   .anyRequest().permitAll()
		   .and()
		     .formLogin().loginPage("/login")
		     .usernameParameter("email").passwordParameter("password")
		     .defaultSuccessUrl("/tarea", true)
		     .permitAll()
		   .and()
		     .logout().logoutSuccessUrl("/logout")
		     .permitAll()
		   .and()
		    .exceptionHandling().accessDeniedPage("/403")
		   .and()
		     .csrf();		
	}

	@Bean(name = "passwordEncode")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
