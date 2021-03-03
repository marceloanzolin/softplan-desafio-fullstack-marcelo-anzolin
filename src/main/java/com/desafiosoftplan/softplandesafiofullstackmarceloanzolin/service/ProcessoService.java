package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service;

import java.util.List;
import java.util.Optional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;

public interface ProcessoService {

	Processo salvarProcesso(Processo processo);

	Processo atualizar(Processo processo);

	void validarProcesso(Processo processo);

	Optional<Processo> obterPorId(Long codProcesso);

	List<Processo> buscarTodosProcessos();

}
