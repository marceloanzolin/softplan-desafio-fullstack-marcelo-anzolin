package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.ErroAutenticacao;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository.ProcessoRepository;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository.UsuarioRepository;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.ProcessoService;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.UsuarioService;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	private ProcessoRepository processorepository;// como não acessa direto a base de dados

	@Autowired
	public ProcessoServiceImpl(ProcessoRepository processorepository) {
		super();
		this.processorepository = processorepository;
	}

	
	@Override
	@Transactional
	public Processo salvarProcesso(Processo processo) {
	
		validarProcesso(processo);
		
		return processorepository.save(processo);
	}


	@Override
	@Transactional
	public Processo atualizar(Processo processo) {
		validarProcesso(processo);
		
		Objects.requireNonNull(processo.getCodProcesso());
		return processorepository.save(processo);
	}


	@Override
  @Transactional(readOnly = true)
	public List<Processo> buscarTodosProcessos() {

	return processorepository.findAll();

	}

//	@Override
	//public void atualizarParecer(Usuario usuario, TipoUsuario tpusuario) {
		//usuario.setTpUsuario(tpusuario);
		//atualizar(usuario);
	//}

	@Override
	public void validarProcesso(Processo processo) {
		if (processo.getNomeProcesso()== null || processo.getNomeProcesso().trim().equals("")) {
			throw new RNException("Informe o Nome do Processo");
		}

		if (processo.getDescricaoProcesso() == null || processo.getDescricaoProcesso().trim().equals("")) {
			throw new RNException("Informe a descrição do Processo");
		}

	}

	@Override
	public Optional<Processo> obterPorId(Long codProcesso) {

		return processorepository.findById(codProcesso);
	}


}
