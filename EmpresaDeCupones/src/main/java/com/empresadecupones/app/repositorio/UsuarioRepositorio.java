package com.empresadecupones.app.repositorio;



import com.empresadecupones.app.modelo.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{


	
}