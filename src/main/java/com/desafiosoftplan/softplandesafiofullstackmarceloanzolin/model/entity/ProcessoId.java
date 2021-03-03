package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade para representar a chave composta da Classe {@see ProcessoUsuario.java}.
 * Utilizado para realização dos mapeamentos a biblioteca @Lombok.
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see <package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProcessoId implements Serializable{

	private long codprocesso;
	private long codusuariofinalizador;
	
	
}
