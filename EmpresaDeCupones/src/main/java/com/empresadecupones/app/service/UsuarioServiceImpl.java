package com.empresadecupones.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empresadecupones.app.modelo.entity.Usuario;

import com.empresadecupones.app.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private UsuarioRepositorio repositorio; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	public Integer altaUsuario(Usuario usuario) {
		
		//Codifica la contrasena antes de guardarla en la base de datos
		usuario.setContrasena(bCryptEncoder.encode(usuario.getContrasena()));
		System.out.println("Servicio altaUsuario() fue ejecutado");
		return repositorio.save(usuario).getId();
	}
	
	
	@Override
	public Optional<Usuario> listarPorId(int id) {
		System.out.println("Servicio  listarPorId()  fue ejecutado");
		return repositorio.findById(id);
	}


	@Override
	public List<Usuario> listarUsuarios() {
		System.out.println("Servicio  listarUsuario()  fue ejecutado");
		return repositorio.findAll();
	}





	




}