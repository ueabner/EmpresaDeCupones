package com.empresadecupones.app.modelo.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="jwt_usuario")
public class JwtUsuario {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="contrasena")
	private String contrasena;
	
	@Column(name="email")
	private String email;
	
	@ElementCollection(fetch= FetchType.EAGER)
	@CollectionTable(
			name="roles",
			joinColumns = @JoinColumn(name="id")
			)
	@Column(name="role")
	private Set<String> roles;
	
	
}