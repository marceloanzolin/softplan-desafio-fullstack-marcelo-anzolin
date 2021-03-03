package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository.ProcessoRepository;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.ProcessoService;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	private ProcessoRepository processoRepository;

	@Autowired
	public ProcessoServiceImpl(ProcessoRepository processoRepository) {
		super();
		this.processoRepository = processoRepository;
	}

	@Override
	@Transactional
	public Processo salvarProcesso(Processo processo) {

		validarProcesso(processo);

		return processoRepository.save(processo);
	}

	@Override
	@Transactional
	public Processo atualizar(Processo processo) {
		validarProcesso(processo);

		Objects.requireNonNull(processo.getCodProcesso());
		return processoRepository.save(processo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Processo> buscarTodosProcessos() {

		return processoRepository.findAll();

	}

	@Override
	public void validarProcesso(Processo processo) {
		if (processo.getNomeProcesso() == null || processo.getNomeProcesso().trim().equals("")) {
			throw new RNException("Informe o Nome do Processo");
		}

		if (processo.getDescricaoProcesso() == null || processo.getDescricaoProcesso().trim().equals("")) {
			throw new RNException("Informe a descrição do Processo");
		}

	}

	@Override
	public Optional<Processo> obterPorId(Long codProcesso) {

		return processoRepository.findById(codProcesso);
	}

}
