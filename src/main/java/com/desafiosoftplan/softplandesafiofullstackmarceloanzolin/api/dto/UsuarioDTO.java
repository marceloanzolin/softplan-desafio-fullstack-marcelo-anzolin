package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe de {@see Usuario.java} tipo DTO para otimizar a comuniucação com o cliente  .
 * Utilizado para realização dos mapeamentos a biblioteca @Lombok. <p>
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto</a>
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	private Long codUsuario;
	
	private String nmUsuario;

	private String emailUsuario;

	private String senhaUsuario;

	private String tpUsuario;

}
