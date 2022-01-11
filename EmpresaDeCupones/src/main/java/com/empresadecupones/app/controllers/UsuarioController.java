package com.empresadecupones.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.empresadecupones.app.modelo.entity.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.empresadecupones.app.service.IUsuarioService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = { "/" })
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping( "/listar-usuarios" )
	public List<Usuario> listarUsuario() {
		final List<Usuario> usuarios = usuarioService.listarUsuarios();
		return usuarios;
	}

	@PostMapping("/alta-usuario")
	public ResponseEntity<String> altaUsuario(@RequestBody Usuario usuario) {
		Integer id = usuarioService.altaUsuario(usuario);
		String message = "Usuario con '" + id + "' guardado exitosamente";
		return ResponseEntity.ok(message);
	}

}