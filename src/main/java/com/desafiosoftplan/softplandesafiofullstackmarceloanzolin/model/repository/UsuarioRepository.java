package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;

/**
 * Interface de de Repository para operações na base de dados referente a classe
 * de {@see Usuario.java}
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Método responsável verificar se o email existe.
	 * 
	 * @param String contendo o email do Usuário
	 * @return boolean True se o email existir e False caso não exista
	 */
	boolean existsByEmailUsuario(String email);

	/**
	 * Método responsável por recuperar o objeto {@see Usuario.java} pelo email.
	 * 
	 * @param String contendo o email do Usuário
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> findByEmailUsuario(String email);

	/**
	 * Método responsável por recuperar um List<Usuario> com todos os
	 * {@see Usuario.java} encontrados na base .
	 * 
	 * @return List<Usuario>
	 */
	List<Usuario> findAll();

}
