package com.empresadecupones.app.jwt.util;

import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Clase responsable de realizar operaciones JWT como creacion y validacion.
 *  Hace uso de io.jsonwebtoken.Jwts para lograr esto.
 */

@Component
public class JWTUtil {

	@Value("${app.secret.key}")
	private String secret_key;

	// codigo para generar el Token
	public String generaToken(String subject) {
		String tokenId= String.valueOf(new Random().nextInt(10000));
		return Jwts.builder()
				.setId(tokenId)
				.setSubject(subject)
				.setIssuer("ABC_Ltd")
				.setAudience("XYZ_Ltd")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secret_key.getBytes()))
				.compact();
	}

	// coidgo para obtener el reclamo
	public Claims getClaims(String token) {

		return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(secret_key.getBytes()))
				.parseClaimsJws(token)
				.getBody();
	}

	// valida si el Token es valido
	public boolean isTokenValido(String token) {
		return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
	}
	
	// valida si el Token es valido por usuario
	public boolean isTokenValido(String token,String usuario) {
		String tokenUserName=getSubject(token);
		return (usuario.equals(tokenUserName) && !isTokenExpirado(token));
	}
	
	// valida si el token expiro
	public boolean isTokenExpirado(String token) {
		return getFechaExpiracion(token).before(new Date(System.currentTimeMillis()));
	}
	
	//obtiene fecha de expiracion
	public Date getFechaExpiracion(String token) {
		return getClaims(token).getExpiration();
	}
	
	//codigo para obtener fecha de expiracion
	public String getSubject(String token) {
		return getClaims(token).getSubject();
	}
}