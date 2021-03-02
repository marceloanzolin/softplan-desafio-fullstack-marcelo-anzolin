package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service;

import java.util.List;
import java.util.Optional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario);

	void validarEmail(String email);

	Usuario atualizar(Usuario usuario);

	void deletar(Usuario usuario);

	List<Usuario> buscar(Usuario usuarioFiltro);

	public void atualizarStatus(Usuario usuario, TipoUsuario tpusuario);
	
	void validarUsuario(Usuario usuario);
	
	Optional<Usuario> obterPorId(Long codUsuario);
	
	List<Usuario> buscarTodosUsuarios();
	

}
