package com.empresadecupones.app.modelo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUsuarioResponse {

	private String token;
	private String mensaje;
}