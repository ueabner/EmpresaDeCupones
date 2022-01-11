package com.empresadecupones.app.modelo.entity;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno")
	private String apellidoMaterno;
	
	@Column(name = "email")
		private String email;
	
	@Column(name = "contrasena")
		private String contrasena;
	
	@Column(name = "usuario_refirio")
	private String usuario_refirio;
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
	}

	public Usuario(final int id) {
		this.id = id;
	}

	public Usuario(final int id, final String nombre, final String apellidoPaterno, final String apellidoMaterno,
			final String email, final String contrasena, final String usuario_refirio) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.contrasena = contrasena;
		this.usuario_refirio = usuario_refirio;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(final String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(final String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(final String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUsuario_refirio() {
		return this.usuario_refirio;
	}

	public void setUsuario_refirio(final String usuario_refirio) {
		this.usuario_refirio = usuario_refirio;
	}

	public static long getSerialversionuid() {
		return 1L;
	}
}