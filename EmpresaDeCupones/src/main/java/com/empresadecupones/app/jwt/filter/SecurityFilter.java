package com.empresadecupones.app.jwt.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.empresadecupones.app.jwt.util.JWTUtil;

/*
 * Esta clase se ejecuta para cualquier solicitud entrante.
 *  Comprueba si la solicitud tiene un token JWT valido. 
 *  Si tiene un token JWT valido, establece la autenticacion 
 *  para especificar que el usuario actual esta autenticado.
 */

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil util;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//Valida el token del Authorization Header
		String token= request.getHeader("Authorization");
		if(token !=null) {
			String usuario= util.getSubject(token);
			
			if(usuario !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails user= userDetailsService.loadUserByUsername(usuario);
				boolean isValid=util.isTokenValido(token, user.getUsername());
				
				if(isValid) {
					UsernamePasswordAuthenticationToken authToken= 
							new UsernamePasswordAuthenticationToken(usuario, user.getPassword(), user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
							
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}