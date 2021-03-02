package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.UsuarioDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.ErroAutenticacao;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

	private final UsuarioService usuarioService;

	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO usuDto) {

		try {

			Usuario usuarioAutenticado = usuarioService.autenticar(usuDto.getEmailUsuario(), usuDto.getSenhaUsuario());
			return ResponseEntity.ok(usuarioAutenticado);

		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO usuDto) {

		Usuario usuarioConvertido = converter(usuDto);

		try {
			Usuario usuario = usuarioService.salvarUsuario(usuarioConvertido);
			
			return new ResponseEntity(usuario, HttpStatus.CREATED);
		} catch (RNException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// @PutMapping("{codUsuario}/teste")
	@PutMapping("{codUsuario}")
	public ResponseEntity atualizar(@PathVariable("codUsuario") Long codUsuario, @RequestBody UsuarioDTO usuDto) {

		return usuarioService.obterPorId(codUsuario).map(usuarioReturn -> {
			try {
				Usuario usuarioConvertido = converter(usuDto);
				usuarioConvertido.setCodUsuario(usuarioReturn.getCodUsuario());
				usuarioService.atualizar(usuarioConvertido);

				return ResponseEntity.ok(usuarioConvertido);
			} catch (RNException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet(() -> new ResponseEntity("Usuário não encontrado!", HttpStatus.BAD_REQUEST));
	}

	@GetMapping("{id}")
	public ResponseEntity obterUsuarioPorID(@PathVariable("id") Long id) {

		return usuarioService.obterPorId(id)
				.map(usuario -> new ResponseEntity(converterUsuarioDTO(usuario), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("{codUsuario}")
	public ResponseEntity deletar(@PathVariable("codUsuario") Long codUsuario) {

		return usuarioService.obterPorId(codUsuario).map(usuarioReturn -> {
			try {
				usuarioService.deletar(usuarioReturn);
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			} catch (RNException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet(() -> new ResponseEntity("Usuário não encontrado!", HttpStatus.BAD_REQUEST));
	}

	@GetMapping
	public ResponseEntity buscar(@RequestParam(value = "nmusuario", required = false) String nmUsuario) {

		List<Usuario> listaUsuarios;

		if (nmUsuario != null) {
			Usuario usuarioFiltro = new Usuario();

			usuarioFiltro.setNmUsuario(nmUsuario);

			listaUsuarios = usuarioService.buscar(usuarioFiltro);
		} else {
			listaUsuarios = usuarioService.buscarTodosUsuarios();
		}

		return ResponseEntity.ok(listaUsuarios);
	}

	private Usuario converter(UsuarioDTO usuarioDto) {

		Usuario usuario = new Usuario();

		if (usuarioDto.getCodUsuario() != null) {
			usuario.setCodUsuario(usuarioDto.getCodUsuario());
		}

		usuario.setNmUsuario(usuarioDto.getNmUsuario());
		usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
		usuario.setSenhaUsuario(usuarioDto.getSenhaUsuario());

		if (usuarioDto.getTpUsuario() != null) {
			usuario.setTpUsuario(TipoUsuario.valueOf(usuarioDto.getTpUsuario()));
		}

		return usuario;
	}

	private UsuarioDTO converterUsuarioDTO(Usuario usuario) {

		return UsuarioDTO.builder().codUsuario(usuario.getCodUsuario()).nmUsuario(usuario.getNmUsuario())
				.emailUsuario(usuario.getEmailUsuario()).senhaUsuario(usuario.getSenhaUsuario())
				.tpUsuario(usuario.getTpUsuario().name()).build();

	}

}
