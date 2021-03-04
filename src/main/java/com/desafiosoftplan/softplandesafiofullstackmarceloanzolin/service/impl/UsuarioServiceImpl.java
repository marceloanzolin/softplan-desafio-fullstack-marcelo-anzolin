package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.ErroAutenticacao;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository.UsuarioRepository;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.UsuarioService;

/**
 * Implementação do serviço de {@see UsuarioService.java}
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.impl
 */

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Email não encontrado.");
		}
		if (!usuario.get().getSenhaUsuario().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {

		validarUsuario(usuario);
		validarEmail(usuario.getEmailUsuario());

		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {

		boolean existeEmail = usuarioRepository.existsByEmailUsuario(email);

		if (existeEmail) {
			throw new RNException("Email informado já existente!");
		}
	}

	@Override
	@Transactional
	public Usuario atualizar(Usuario usuario) {

		validarUsuario(usuario);
		Objects.requireNonNull(usuario.getCodUsuario());
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void deletar(Usuario usuario) {

		Objects.requireNonNull(usuario.getCodUsuario());
		usuarioRepository.delete(usuario);
	}

	@Override
	public Optional<Usuario> obterPorId(Long codUsuario) {

		return usuarioRepository.findById(codUsuario);
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodosUsuarios() {

		return usuarioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> buscaUsuarioPorTpUsuario(TipoUsuario tpUsuario){
		return usuarioRepository.findByTpUsuario(tpUsuario);
	}

	@Override
	public void validarUsuario(Usuario usuario) {
		if (usuario.getNmUsuario() == null || usuario.getNmUsuario().trim().equals("")) {
			throw new RNException("Informe o Nome do Usuário");
		}

		if (usuario.getEmailUsuario() == null || usuario.getEmailUsuario().trim().equals("")) {
			throw new RNException("Informe o Email");
		}

		if (usuario.getSenhaUsuario() == null || usuario.getSenhaUsuario().trim().equals("")) {
			throw new RNException("Informe a Senha");
		}

		if (usuario.getSenhaUsuario() == null || usuario.getSenhaUsuario().length() < 3) {
			throw new RNException("A senha deve ter ao Menos 3 Caracteres");
		}

		if (usuario.getTpUsuario() == null) {
			throw new RNException("Informe o tipo do Usuário");
		}

	}

}
