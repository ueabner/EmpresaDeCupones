package com.empresadecupones.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresadecupones.app.modelo.entity.JwtUsuario;
import com.empresadecupones.app.modelo.entity.JwtUsuarioRequest;
import com.empresadecupones.app.modelo.entity.JwtUsuarioResponse;
import com.empresadecupones.app.service.IJwtUsuarioService;
import com.empresadecupones.app.jwt.util.JWTUtil;

@Controller
@RequestMapping("/jwt")
public class JwtController {

	@Autowired
	private IJwtUsuarioService usuarioService;

	@Autowired
	private JWTUtil util;

	@Autowired
	private AuthenticationManager authenticationManager;
	

	/*
	 * *  	Dar de alta usuario para realizar login y obtener token con el sig formato:
	 * 				{
					"usuario": "",
					"contrasena": "",
					"email": "",
					"roles": ["",""]
					}
	 */
	@PostMapping("alta-usuario")
	public ResponseEntity<String> saveUsuario(@RequestBody JwtUsuario usuario) {

		Integer id = usuarioService.saveUsuario(usuario);
		String mensaje = "Usuario con id '" + id + "' guardado exitosamente";
		return ResponseEntity.ok(mensaje);
		
	}

	
	/*
	 * * 	Login para obtener token jwt con el sig formato:
					{
					"usuario": "",
					"contrasena": ""
					}
	 */

	@PostMapping("login")
	public ResponseEntity<JwtUsuarioResponse> login(@RequestBody JwtUsuarioRequest request) {

		// Validar nombre de usuario/contrasena con DB
		// (requerido en caso de autenticacion Stateless)
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasena()));
		String token = util.generaToken(request.getUsuario());
		return ResponseEntity.ok(new JwtUsuarioResponse(token, "Token generado exitosamente!"));
	}

}