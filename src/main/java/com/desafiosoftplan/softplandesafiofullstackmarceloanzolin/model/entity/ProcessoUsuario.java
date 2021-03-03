package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoStatusProcesso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe Entidade responsável por mapear a tabela que armazena os processos vinculados de um usuário.
 * Utilizado para realização dos mapeamentos a biblioteca @Lombok.
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see <package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity>
 */

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
