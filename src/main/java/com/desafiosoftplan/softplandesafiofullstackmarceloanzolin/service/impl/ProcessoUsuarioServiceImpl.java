package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository.ProcessoUsuarioRepository;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.ProcessoUsuarioService;

@Service
public class ProcessoUsuarioServiceImpl implements ProcessoUsuarioService {

	private ProcessoUsuarioRepository processoUsuarioRepository;

	@Autowired
	public ProcessoUsuarioServiceImpl(ProcessoUsuarioRepository processoUsuarioRepository) {
		super();
		this.processoUsuarioRepository = processoUsuarioRepository;
	}

	@Override
	@Transactional
	public ProcessoUsuario salvarProcessoUsuario(ProcessoUsuario processoUsuario) {

		validarProcessoUsuario(processoUsuario);

		return processoUsuarioRepository.save(processoUsuario);
	}

	@Override
	@Transactional
	public ProcessoUsuario incluirParecer(ProcessoUsuario processoUsuario) {

		validarProcessoUsuario(processoUsuario);

		Objects.requireNonNull(processoUsuario.getCodProcessoUsuario().getCodprocesso());
		Objects.requireNonNull(processoUsuario.getCodProcessoUsuario().getCodusuariofinalizador());
		return processoUsuarioRepository.save(processoUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProcessoUsuario> obterProcessoUsuario(Long codProcesso, Long codUsuario) {
		return processoUsuarioRepository.findByProcessoUsuarioCustom(codProcesso, codUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProcessoUsuario> buscarProcessoUsuarioStatus(Long codUsuarioFinalizador, String tpStatus) {

		return processoUsuarioRepository.findByProcessoUsuarioStatusCustom(codUsuarioFinalizador, tpStatus);

	}

	@Override
	public void validarProcessoUsuario(ProcessoUsuario processoUsuario) {

		if (processoUsuario.getUsuarioTriador() == null) {
			throw new RNException("Informe o Usuário Triador");
		}

		if (processoUsuario.getCodProcessoUsuario().getCodprocesso() == 0) {
			throw new RNException("Informe o Código do Processo");
		}

		if (processoUsuario.getCodProcessoUsuario().getCodusuariofinalizador() == 0) {
			throw new RNException("Informe o Usuário Finalizador");
		}

		if (processoUsuario.getStatusProcesso() == null || processoUsuario.getStatusProcesso().equals("")) {
			throw new RNException("Informe o Status do Processo ");
		}

	}

	@Override
	public Optional<ProcessoUsuario> obterPorProcessoUsuario(Long codUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
