package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoStatusProcesso;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
