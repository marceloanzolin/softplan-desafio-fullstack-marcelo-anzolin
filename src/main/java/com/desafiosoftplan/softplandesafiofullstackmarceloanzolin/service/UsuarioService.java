package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service;

import java.util.List;
import java.util.Optional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;

/**
 * Interface de serviço para as operação referentes as classe de {@see Usuario.java}
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see <package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service</a>
 */

public interface UsuarioService {
	
	/**
	 * Método responsável por realizar a operação de login do Usuário.
	 * 
	 * @param email String contendo o email do Usuário
	 * @param senha String contendo o  senha do Usuário
	 * @return Optional<Usuario> caso exista o usuário na base de dados, se parametros nullos 
	 * @throws exception throw new ErroAutenticacao
	 */
	Usuario autenticar(String email, String senha);

	/**
	 * Método responsável por persistir a entidados {@see Usuario.java} .
	 * 
	 * @param usuario Objeto {@see Usuario.java} contendo os dados a serem persistidos
	 * @return Usuario  persistido
	 * @throws exception throw new ErroAutenticacao
	 */
	Usuario salvarUsuario(Usuario usuario);

	/**
	 * Método responsável por validar se o email já existe na base .
	 * 
	 * @param email string contendo o email
	 * @throws throw new RNException
	 */
	void validarEmail(String email);

	/**
	 * Método responsável atualizar os dados do Objeto de {@see Usuario.java} .
	 * 
	 * @param Usuario Objeto contendo os dados a serem modificados
	 * @return Usuario com os dados atualizados
	 */
	Usuario atualizar(Usuario usuario);

	/**
	 * Método responsável por remover o objeto passado por parametro do tipo {@see Usuario.java} .
	 * 
	 * @param Usuario Objeto a ser removido
	 */
	void deletar(Usuario usuario);
	
	/**
	 * Método que recupera um  {@see Usuario.java} em especifico, pela chave primária.
	 * 
	 * @param Long Contendo o código do usuário a ser recuperado
	 * @return Optional<Usuario> contendo o usuário recuperado
	 * 
	 */
	Optional<Usuario> obterPorId(Long codUsuario);
	
	/**
	 * Método que recupera uma lista de {@see Usuario.java} existentes na base de dados.
	 * 
	 * @return List<Usuario> de {@see Usuario.java}
	 * 
	 */
	List<Usuario> buscarTodosUsuarios();
	
	/**
	 * Método responsável por validar as informações do objeto {@see Usuario.java}
	 * passado por parametro.
	 * 
	 * @return List<Usuario> de {@see Usuario.java}
	 * @throws throw new RNException com a informação do erro
	 */
	void validarUsuario(Usuario usuario);
	

}
