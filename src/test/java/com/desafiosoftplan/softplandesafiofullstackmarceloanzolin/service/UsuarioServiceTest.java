package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	@Autowired 
	UsuarioService service;
	
	@Autowired 
	UsuarioRepository repository;
	
	@Test
	public void devAutenticarUsuario() {
		
		String email = "finalizador2@gmail.com";
		String senha = "12356";
		
		Usuario usuario  = Usuario.builder().emailUsuario(email).senhaUsuario(senha).codUsuario(1).build();
		
		Mockito.when(repository.findByEmailUsuario(email)).thenReturn(Optional.of(usuario));
		Usuario result = service.autenticar(email, senha);
		org.assertj.core.api.Assertions.assertThat(result).isNotNull();
	}
	
	@Test
	public void deveValidarEmail() {
		
		 repository.deleteAll();
		 
		 service.validarEmail("finalizador2@gmail.com");
	}

	@Test
	public void lancarErroAoValidarSeEmailCadastrado() {
	
		Usuario usuario = Usuario.builder().nmUsuario("marcelo").emailUsuario("mtavares@gmail.br").senhaUsuario("123").build();
		 
		repository.save(usuario);
		
	   Assertions.assertThrows(RNException.class, () -> {
			service.validarEmail("finalizador2@gmail.com");
	    });
		
		
		 
	}
}
