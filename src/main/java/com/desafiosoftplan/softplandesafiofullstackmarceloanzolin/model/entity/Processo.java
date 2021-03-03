package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe Entidade responsável por mapear a tabela que armazena os processos
 * Utilizado para realização dos mapeamentos a biblioteca @Lombok.
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see <package
 *      com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity>
 */

@Entity
@Table(name = "processo", schema = "processos")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Processo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codprocesso")
	private long codProcesso;

	@Column(name = "nomeprocesso")
	private String nomeProcesso;

	@Column(name = "descricaoprocesso")
	private String descricaoProcesso;

}
