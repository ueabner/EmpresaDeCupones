package com.empresadecupones.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.empresadecupones.app.modelo.entity.Usuario;
import com.empresadecupones.app.repositorio.UsuarioRepositorio;


@SpringBootTest
class EmpresaDecuponesApplicationTests {
	
	
	@Autowired
	UsuarioRepositorio repositorio;

	@Test
	public void testAltaUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setId(6);
		usuario.setNombre("Juan");
		usuario.setApellidoPaterno("Perez");
		usuario.setApellidoMaterno("Lopez");
		usuario.setContrasena("Mypassw123");
		usuario.setEmail("jpl@gmail.com");
		usuario.setUsuario_refirio("Maria Lopez Perez");
		repositorio.save(usuario);
		assertNotNull(repositorio.findById(6).get());
	}

	@Test
	public void testListarUsuarios() {
		List<Usuario> usuarios = repositorio.findAll();
		assertThat(usuarios).size().isGreaterThan(0);
	}	
	
	@Test
	public void testListarUsuarioPorId() {
		Usuario usuario = repositorio.findById(6).get();
		assertEquals("Juan", usuario.getNombre());
	}
	
	@Test
	public void testUsuarioUpdate() {
		Usuario usuario = repositorio.findById(6).get();
		usuario.setNombre("Mario");
		repositorio.save(usuario);
		assertNotEquals("Juan", repositorio.findById(6).get().getNombre());
	}
	
	@Test
	public void testDelete() {
		repositorio.deleteById(6);
		assertThat(repositorio.existsById(6)).isFalse();
	}
	
	
	

}
