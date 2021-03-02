package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service;

import java.util.List;
import java.util.Optional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoUsuario;
public interface ProcessoUsuarioService {


	ProcessoUsuario salvarProcessoUsuario(ProcessoUsuario processoUsuario);

	ProcessoUsuario incluirParecer(ProcessoUsuario processoUsuario);
	
	Optional<ProcessoUsuario> obterPorProcessoUsuario(Long codUsuario);
	
	List<Processo> buscarTodosProcessos();
	
	void validarProcessoUsuario(ProcessoUsuario processoUsuario);
	

}
