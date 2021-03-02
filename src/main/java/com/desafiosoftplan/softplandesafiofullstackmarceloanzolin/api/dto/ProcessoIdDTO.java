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
public class ProcessoIdDTO {
	
	private Long codProcesso;
	
	private Long codusUsuarioFinalizador;

}
