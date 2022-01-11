package com.empresadecupones.app.service;

import java.util.Optional;

import com.empresadecupones.app.modelo.entity.JwtUsuario;


public interface IJwtUsuarioService {

	Integer saveUsuario(JwtUsuario usuario);
	
	Optional<JwtUsuario> findByUsuario(String usuario);
}