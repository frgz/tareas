/**
 * 
 */
package com.frgz.tareas.config;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.frgz.tareas.core.authentication.TareaAuthenticationProvider;

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
	private TareaAuthenticationProvider tareaAutenticationProvider;
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    	
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(tareaAutenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Configuracion de seguridad
		httpSecurity.authorizeRequests()
		   .antMatchers("/webjars/**", "/resources/**", "/login/**").permitAll()
		   .antMatchers("/login").permitAll()
		   .antMatchers("/tarea**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
		   .antMatchers("/lista**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
		   .anyRequest().permitAll()
		   .and()
		     .formLogin().loginPage("/login")
		     .usernameParameter("email").passwordParameter("password")
		     .defaultSuccessUrl("/lista", true)
		     .permitAll()
		   .and()
		     .logout().logoutSuccessUrl("/login?logout")
		     .permitAll()
		   .and()
		    .exceptionHandling().accessDeniedPage("/403")
		   .and()
		     .csrf();		
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth
                .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }	

	@Bean(name = "passwordEncode")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
