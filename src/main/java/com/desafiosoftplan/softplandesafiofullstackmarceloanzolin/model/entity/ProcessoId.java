package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProcessoId implements Serializable{

	private long codprocesso;
	private long codusuariofinalizador;
	
	
}
