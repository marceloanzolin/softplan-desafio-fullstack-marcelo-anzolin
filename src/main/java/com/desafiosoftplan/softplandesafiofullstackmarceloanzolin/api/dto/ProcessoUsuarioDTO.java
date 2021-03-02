package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.api.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoUsuarioDTO {
	
	private long codProcesso;
	
	private long codUsuarioFinalizador;

	private Long codUsuarioTriador;
	
	private String parecerProcesso;
	
	private String statusProcesso;

}
