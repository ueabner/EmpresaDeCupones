package com.empresadecupones.app.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresadecupones.app.modelo.entity.JwtUsuario;

public interface JwtUsuarioRepository extends JpaRepository<JwtUsuario, Integer> {
 
	Optional<JwtUsuario> findByUsuario(String usuario);
}