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

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;// como não acessa direto a base de dados

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmailUsuario(email);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Email não encontrado.");
		}
		if (!usuario.get().getSenhaUsuario().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		return usuario.get();
	}

	@Override
	@Transactional // abre a transação e comita
	public Usuario salvarUsuario(Usuario usuario) {
		validarUsuario(usuario);
		validarEmail(usuario.getEmailUsuario());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {

		boolean existeEmail = repository.existsByEmailUsuario(email);
		if (existeEmail) {
			throw new RNException("Email informado já existente!");
		}

	}

	@Override
	@Transactional
	public Usuario atualizar(Usuario usuario) {
		validarUsuario(usuario);
		Objects.requireNonNull(usuario.getCodUsuario());
		return repository.save(usuario);
	}

	@Override
	@Transactional // abre a transação e comita
	public void deletar(Usuario usuario) {
		Objects.requireNonNull(usuario.getCodUsuario());
		repository.delete(usuario);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscar(Usuario usuarioFiltro) {

		return repository.findByUsuarioCustom(usuarioFiltro.getNmUsuario());

	}

	@Override
	public void atualizarStatus(Usuario usuario, TipoUsuario tpusuario) {
		usuario.setTpUsuario(tpusuario);
		atualizar(usuario);
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

	@Override
	public Optional<Usuario> obterPorId(Long codUsuario) {

		return repository.findById(codUsuario);
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodosUsuarios() {
		return repository.findAll();
	}

}
