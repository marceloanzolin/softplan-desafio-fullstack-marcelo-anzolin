package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.resource;

import java.util.List;

import org.hibernate.loader.plan.exec.process.internal.AbstractRowReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.ProcessoDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.ProcessoIdDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.ProcessoUsuarioDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoId;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoStatusProcesso;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.ProcessoService;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.ProcessoUsuarioService;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/processosusuario")
@RequiredArgsConstructor
public class ProcessoUsuarioResource {

	private final ProcessoUsuarioService processoUsuarioService;
	private final ProcessoService processoService;
	private final UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity salvar(@RequestBody ProcessoUsuarioDTO processoUsuarioDTO) {

		ProcessoUsuario processoUsuarioConvertido = converterProcessoUsuario(processoUsuarioDTO);

		try {
			
			ProcessoUsuario processoUsuario = processoUsuarioService.salvarProcessoUsuario(processoUsuarioConvertido);

			return new ResponseEntity(processoUsuario, HttpStatus.CREATED);
		} catch (RNException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{codprocesso}/{codusuariofinalizador}")
	public ResponseEntity inserirParecer( @PathVariable("codprocesso") Long codProcesso , @PathVariable("codusuariofinalizador") Long codUsuarioFinalizador ,@RequestBody ProcessoUsuarioDTO processoUsuarioDTO ) {
		
		return  processoUsuarioService.obterProcessoUsuario(codProcesso,codUsuarioFinalizador).map(processoUsuarioReturn -> {
			try {
				System.out.println(processoUsuarioDTO);
				ProcessoUsuario processoUsuarioConvertido = converterProcessoUsuario(processoUsuarioDTO);

				processoUsuarioConvertido.setCodProcessoUsuario(
						new ProcessoId(processoUsuarioDTO.getCodProcesso(), processoUsuarioDTO.getCodUsuarioFinalizador()));
				
				processoUsuarioConvertido.setParecerProcesso(processoUsuarioDTO.getParecerProcesso());
				processoUsuarioConvertido.setStatusProcesso(TipoStatusProcesso.F);

				processoUsuarioService.incluirParecer(processoUsuarioConvertido);

				return ResponseEntity.ok(processoUsuarioConvertido);
			} catch (RNException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet(() -> new ResponseEntity("Usuário não encontrado!", HttpStatus.BAD_REQUEST));
		
	}

	/*
	 * @GetMapping("{id}") public ResponseEntity
	 * obterUsuarioPorID(@PathVariable("codProcesso") Long codProcesso) {
	 * 
	 * return processoService.obterPorId(codProcesso) .map(processo -> new
	 * ResponseEntity(converterProcessoDTO(processo), HttpStatus.OK)) .orElseGet(()
	 * -> new ResponseEntity(HttpStatus.NOT_FOUND)); }
	 * 
	 * 
	 * @GetMapping public ResponseEntity buscarTodosProcessos() {
	 * 
	 * List<Processo>listaProcessos = processoService.buscarTodosProcessos();
	 * 
	 * 
	 * return ResponseEntity.ok(listaProcessos); }
	 * 
	 */
	private ProcessoUsuario converterProcessoUsuario(ProcessoUsuarioDTO processoUsuarioDTO) {

		ProcessoUsuario processoUsuario = new ProcessoUsuario();

		if (processoUsuarioDTO.getCodProcesso() != 0 && processoUsuarioDTO.getCodUsuarioFinalizador() != 0) {
			processoUsuario.setCodProcessoUsuario(
					new ProcessoId(processoUsuarioDTO.getCodProcesso(), processoUsuarioDTO.getCodUsuarioFinalizador()));
		}

		if (processoUsuarioDTO.getCodUsuarioTriador() != 0 || processoUsuarioDTO.getCodUsuarioTriador() != null) {
			Usuario usuarioTriador = usuarioService.obterPorId(processoUsuarioDTO.getCodUsuarioTriador())
					.orElseThrow(() -> new RNException("Usuário triador não encontrado."));

			processoUsuario.setUsuarioTriador(usuarioTriador);
		}

		if (!processoUsuarioDTO.getStatusProcesso().isEmpty() || processoUsuarioDTO.getStatusProcesso() != null) {
			processoUsuario.setStatusProcesso(TipoStatusProcesso.valueOf(processoUsuarioDTO.getStatusProcesso()));
		}

		if (processoUsuarioDTO.getParecerProcesso() != null || processoUsuarioDTO.getParecerProcesso() != "") {
			processoUsuario.setParecerProcesso(processoUsuarioDTO.getParecerProcesso());
		}

		return processoUsuario;
	}

	// private ProcessoUsuarioDTO converterProcessoDTO(ProcessoUsuario
	// processoUsuario) {

	// return
	// ProcessoUsuarioDTO.builder().codProcesso(processoUsuario.getCodProcessoUsuario().getCodprocesso()).codUsuarioFinalizador(processoUsuario.getCodProcessoUsuario().getCodusuariofinalizador())
	// .codUsuarioTriador(processoUsuario.getUsuarioTriador().getCodUsuario()).parecerProcesso(processoUsuario.getParecerProcesso()).statusProcesso(processoUsuario.getStatusProcesso().name()).build();

	// }

}
