package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.ProcessoDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.ProcessoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/processos")
@RequiredArgsConstructor
public class ProcessoResource {

	private final ProcessoService processoService;

	@PostMapping
	public ResponseEntity salvar(@RequestBody ProcessoDTO processoDto) {

		Processo processoConvertido = converter(processoDto);

		try {
			Processo processo = processoService.salvarProcesso(processoConvertido);
		
			return new ResponseEntity(processo, HttpStatus.CREATED);
		} catch (RNException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@GetMapping("{id}")
	public ResponseEntity obterUsuarioPorID(@PathVariable("codProcesso") Long codProcesso) {

		return processoService.obterPorId(codProcesso)
				.map(processo -> new ResponseEntity(converterProcessoDTO(processo), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
	}


	@GetMapping
	public ResponseEntity buscarTodosProcessos() {

		List<Processo>listaProcessos = processoService.buscarTodosProcessos();
		

		return ResponseEntity.ok(listaProcessos);
	}
	

	private Processo converter(ProcessoDTO processoDto) {

		Processo processo = new Processo();

		if (processoDto.getCodProcesso() != null) {
			processo.setCodProcesso(processoDto.getCodProcesso());
		}

		processo.setNomeProcesso(processoDto.getNomeProcesso());
		processo.setDescricaoProcesso(processoDto.getDescricaoProcesso());

		return processo;
	}

	private ProcessoDTO converterProcessoDTO(Processo processo) {

		return ProcessoDTO.builder().codProcesso(processo.getCodProcesso()).descricaoProcesso(processo.getDescricaoProcesso())
				.nomeProcesso(processo.getNomeProcesso()).build();

	}

}
