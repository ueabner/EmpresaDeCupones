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
	
	@PostMapping("alta-usuario")
	public ResponseEntity<String> saveUsuario(@RequestBody JwtUsuario usuario) {

		Integer id = usuarioService.saveUsuario(usuario);
		String mensaje = "Usuario con id '" + id + "' guardado exitosamente";
		return ResponseEntity.ok(mensaje);
		
	}

	@PostMapping("login")
	public ResponseEntity<JwtUsuarioResponse> login(@RequestBody JwtUsuarioRequest request) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasena()));
		String token = util.generaToken(request.getUsuario());
		return ResponseEntity.ok(new JwtUsuarioResponse(token, "Token generado exitosamente!"));
	}

}
