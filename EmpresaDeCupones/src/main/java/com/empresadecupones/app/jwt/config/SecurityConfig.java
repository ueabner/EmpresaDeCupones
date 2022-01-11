package com.empresadecupones.app.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.empresadecupones.app.jwt.filter.SecurityFilter;

/*
 * Esta clase amplia WebSecurityConfigurerAdapter. 
 * Permite la personalizacion tanto de WebSecurity como de HttpSecurity.
 * Aqui tambien pueden establecerse las rutas excluidas de autenticacion.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	private AutenticacionDeUsuarioNoAutorizado authenticationEntryPoint;
	
	@Autowired
	private SecurityFilter secFilter;
	
	//Requerido en caso de autenticación sin estado
	@Override @Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService)
		    .passwordEncoder(bCryptEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// Deshabilitamos CSRF porque no usa el inicio de sesion basado en formulario
			.csrf().disable()    
			.authorizeRequests()
			//Rutas excluidas de Autenticacion
			.antMatchers("/jwt/alta-usuario","/jwt/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class)
			;
	}
}