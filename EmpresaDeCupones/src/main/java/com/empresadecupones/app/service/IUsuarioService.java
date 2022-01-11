package com.empresadecupones.app.service;

import java.util.List;
import java.util.Optional;

import com.empresadecupones.app.modelo.entity.Usuario;


public interface IUsuarioService {

	Integer altaUsuario(Usuario usuario);
	
	Optional<Usuario> listarPorId(int id);

	List<Usuario> listarUsuarios();


	

}