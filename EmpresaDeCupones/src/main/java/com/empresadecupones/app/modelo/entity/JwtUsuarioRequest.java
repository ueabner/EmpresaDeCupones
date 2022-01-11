package com.empresadecupones.app.modelo.entity;

import lombok.Data;

@Data
public class JwtUsuarioRequest {

	private String usuario;	
	private String contrasena;
}