package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoStatusProcesso;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "processousuario", schema = "processos")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoUsuario {
	@EmbeddedId
	private ProcessoId codProcessoUsuario;

	@ManyToOne
	@JoinColumn(name = "codusuariotriador")
	private Usuario usuarioTriador;
	
	@Column(name = "parecerprocesso")
	private String parecerProcesso;

	@Column(name = "statusprocesso")
	@Enumerated(value = EnumType.STRING)
	private TipoStatusProcesso statusProcesso;

}
