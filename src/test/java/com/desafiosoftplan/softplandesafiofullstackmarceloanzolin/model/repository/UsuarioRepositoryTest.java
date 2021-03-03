package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void verifcaExisteEmail() {

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		boolean result = repository.existsByEmailUsuario("finalizador2@gmail.com");

		Assertions.assertThat(result).isTrue();

	}

	@Test
	public void retornoDeveSerFalsoUsuariNaoCadastradoComEmail() {

		boolean result = repository.existsByEmailUsuario("finalizador4@gmail.com");

		Assertions.assertThat(result).isFalse();

	}

	@Test
	public void persisteUsuario() {

		Usuario usuario = criarUsuario();

		Usuario novoUsuario = repository.save(usuario);

		Assertions.assertThat(novoUsuario.getCodUsuario()).isNotNull();

	}

	@Test
	public void retornabuscaUsuarioPorEmail() {

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		Optional<Usuario> result = repository.findByEmailUsuario("finalizador2@gmail.com");

		Assertions.assertThat(result.isPresent()).isTrue();

	}

	@Test
	public void naoretornabuscaUsuarioPorEmail() {

		Optional<Usuario> result = repository.findByEmailUsuario("finalizadorteste@gmail.com");

		Assertions.assertThat(result.isPresent()).isFalse();

	}

	public static Usuario criarUsuario() {

		return Usuario.builder().nmUsuario("Marcelo").emailUsuario("mtavares@gmail.com").senhaUsuario("123").build();
	}
}
