package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.ProcessoUsuarioDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
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
         
		processoUsuarioDTO.setStatusProcesso(TipoStatusProcesso.P.name());
		
		ProcessoUsuario processoUsuarioConvertido = converterProcessoUsuario(processoUsuarioDTO);

		try {

			ProcessoUsuario processoUsuario = processoUsuarioService.salvarProcessoUsuario(processoUsuarioConvertido);

			return new ResponseEntity(processoUsuario, HttpStatus.CREATED);
		} catch (RNException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity inserirParecer(
			@RequestBody ProcessoUsuarioDTO processoUsuarioDTO) {

		return processoUsuarioService.obterProcessoUsuario(processoUsuarioDTO.getCodProcesso(), processoUsuarioDTO.getCodUsuarioFinalizador())
				.map(processoUsuarioReturn -> {
					try {
					
						processoUsuarioReturn.setParecerProcesso(processoUsuarioDTO.getParecerProcesso());
						processoUsuarioReturn.setStatusProcesso(TipoStatusProcesso.F);
						
						processoUsuarioService.incluirParecer(processoUsuarioReturn);

						return ResponseEntity.ok(processoUsuarioReturn);
					} catch (RNException e) {
						return ResponseEntity.badRequest().body(e.getMessage());
					}

				}).orElseGet(() -> new ResponseEntity("Processo para este usuário não encontrado!", HttpStatus.BAD_REQUEST));

	}

	@GetMapping
	public ResponseEntity buscarProcessoUsuarioStatus(@RequestParam("codusuariofinalizador") Long codUsuarioFinalizador,
			@RequestParam("tpstatus") String tpStatus) {

		List<ProcessoUsuario> listaProcessosUsuarios = processoUsuarioService
				.buscarProcessoUsuarioStatus(codUsuarioFinalizador, tpStatus);

		return ResponseEntity.ok(listaProcessosUsuarios);
	}

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

		if (processoUsuarioDTO.getParecerProcesso() != null || processoUsuarioDTO.getParecerProcesso() != "") {
			processoUsuario.setParecerProcesso(processoUsuarioDTO.getParecerProcesso());
		}

		if (!processoUsuarioDTO.getStatusProcesso().isEmpty() || processoUsuarioDTO.getStatusProcesso() != null) {
			processoUsuario.setStatusProcesso(TipoStatusProcesso.valueOf(processoUsuarioDTO.getStatusProcesso()));
		}

		return processoUsuario;
	}

}
