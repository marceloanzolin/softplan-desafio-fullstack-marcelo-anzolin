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
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto.UsuarioDTO;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.ErroAutenticacao;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.exception.RNException;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * Classe de resource para disponibilização dos serviços da api para operações
 * de {@see Usuario.java}
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.resource;
 */

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

	private final UsuarioService usuarioService;

	/**
	 * Método POST responsável por realizar a operação de login do Usuário.
	 * 
	 * @param @RequestBody UsuarioDTO com os dados passados para login
	 * @return Response com o {@see Usuario.java} autenticado ou o erro de login
	 */
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO usuDto) {

		try {

			Usuario usuarioAutenticado = usuarioService.autenticar(usuDto.getEmailUsuario(), usuDto.getSenhaUsuario());
			return ResponseEntity.ok(usuarioAutenticado);

		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * Método POST responsável por tratar e persistir o objeto que representa
	 * {@see Usuario.java} passado por parametro
	 * 
	 * @param @RequestBody UsuarioDTO usuDto com os dados a serem armazenados
	 * @return Response com o {@see Usuario.java} presistindo ou o erro na
	 *         persistencia
	 */
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

	/**
	 * Método PUT responsável por tratar e persistir o objeto que representa
	 * {@see Usuario.java} passado por parametro
	 * 
	 * @param Long         {codUsuario} que recupera o objeto {@see Usuario.java} a
	 *                     ser atualizado
	 * @param @RequestBody UsuarioDTO usuDto com os dados do objeto a serem tratado
	 *                     e atualizado
	 * @return Response com o {@see Usuario.java} atualizado ou o erro na
	 *         atualização
	 */
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

	/**
	 * Método Get que recupera o {@see Usuario.java} pela sua chave primária
	 * 
	 * @param Long id do objeto {@see Usuario.java} a ser recuperado
	 * @return Response com o {@see Usuario.java} encontrado ou retonrno de usuário
	 *         não encontrado
	 */
	@GetMapping("{id}")
	public ResponseEntity obterUsuarioPorID(@PathVariable("id") Long id) {

		return usuarioService.obterPorId(id)
				.map(usuario -> new ResponseEntity(converterUsuarioDTO(usuario), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
	}

	/**
	 * Método DELETE que remove um {@see Usuario.java}
	 * 
	 * @param Long codUsuario do objeto {@see Usuario.java} a ser removido
	 * @return Status da transação podendo ser falha ou sucesso
	 */
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

	/**
	 * Método GET que recupera uma lista com os {@see Usuario.java} encontrados
	 * 
	 * @return List<Usuario> com os {@see Usuario.java} encontrados
	 */
	@GetMapping
	public ResponseEntity buscar() {

		List<Usuario> listaUsuarios = usuarioService.buscarTodosUsuarios();

		return ResponseEntity.ok(listaUsuarios);
	}

	/**
	 * Método utilizado para converter um {@see UsuarioDTO.java} em
	 * {@see Usuario.java}
	 * 
	 * @param UsuarioDTO a ser convertido
	 * @return {@see Usuario.java}
	 */
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
	
	/**
	 * Método GET que recupera uma lista com os {@see Usuario.java} 
	 * do tipo F de Finalizador
	 * 
	 * @return List<Usuario> com os {@see Usuario.java} encontrados
	 */
	@GetMapping("/buscausuario")
	public ResponseEntity obterUsuarioPorTpUsuario() {

		List<Usuario> listaUsuarios = usuarioService.buscaUsuarioPorTpUsuario(TipoUsuario.F);
		 
		 return ResponseEntity.ok(listaUsuarios);
	}
	
	/**
	 * Método utilizado para converter um {@see Usuario.java} em
	 * {@see UsuarioDTO.java}
	 * 
	 * @param Usuario a ser convertido
	 * @return {@see UsuarioDTO}
	 */
	private UsuarioDTO converterUsuarioDTO(Usuario usuario) {

		return UsuarioDTO.builder().codUsuario(usuario.getCodUsuario()).nmUsuario(usuario.getNmUsuario())
				.emailUsuario(usuario.getEmailUsuario()).senhaUsuario(usuario.getSenhaUsuario())
				.tpUsuario(usuario.getTpUsuario().name()).build();

	}

}
