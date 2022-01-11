package com.empresadecupones.app.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empresadecupones.app.modelo.entity.JwtUsuario;
import com.empresadecupones.app.repositorio.JwtUsuarioRepository;

@Service
public class JwtUsuarioServiceImpl implements IJwtUsuarioService, UserDetailsService {
	
	@Autowired
	private JwtUsuarioRepository repositorio; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	public Integer saveUsuario(JwtUsuario usuario) {
		
		//Codifica la contrasena antes de guardarla en la base de datos
		usuario.setContrasena(bCryptEncoder.encode(usuario.getContrasena()));
		return repositorio.save(usuario).getId();
	}

	//buscar por usuario
	@Override
	public Optional<JwtUsuario> findByUsuario(String usuario) {
		return repositorio.findByUsuario(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<JwtUsuario> opt = repositorio.findByUsuario(usuario);
		
		org.springframework.security.core.userdetails.User springUser=null;
		
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("El usuario con el nombre: " +usuario +" no fue  encontrado");
		}else {
			JwtUsuario user =opt.get();	//Obtiene usuario de la BD
			Set<String> roles = user.getRoles();
			Set<GrantedAuthority> ga = new HashSet<>();
			for(String role:roles) {
				ga.add(new SimpleGrantedAuthority(role));
			}
			
			springUser = new org.springframework.security.core.userdetails.User(
							usuario,
							user.getContrasena(),
							ga );
		}
		
		return springUser;
	}

}