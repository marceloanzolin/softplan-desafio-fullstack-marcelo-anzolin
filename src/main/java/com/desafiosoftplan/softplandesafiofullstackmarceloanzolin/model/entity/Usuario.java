package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe responsável por mapear a tabela de Usuários.
 * Utilizado para realização dos mapeamentos a biblioteca @Lombok. <p>
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see <package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity</a>
 */

@Entity
@Table(name = "usuario", schema = "processos")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codusuario")
	private long codUsuario;

	@Column(name = "nmusuario")
	private String nmUsuario;

	@Column(name = "emailusuario")
	private String emailUsuario;

	@Column(name = "senhausuario")
	private String senhaUsuario;

	@Column(name = "tpusuario")
	@Enumerated(value = EnumType.STRING)
	private TipoUsuario tpUsuario;

}
