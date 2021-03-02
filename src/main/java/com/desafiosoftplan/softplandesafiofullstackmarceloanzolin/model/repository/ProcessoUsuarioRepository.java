package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoId;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoUsuario;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;

public interface ProcessoUsuarioRepository extends JpaRepository<ProcessoUsuario, ProcessoId>{

	
	
	@Query(value = "SELECT * FROM processos.processousuario WHERE codusuariofinalizador = :codusuariofinalizador)", nativeQuery = true)
	List<ProcessoUsuario> findByProcessoUsuarioCustom(@Param("codusuariofinalizador") String codUsuarioFinalizador);
	
	
	List<ProcessoUsuario> findAll();

}
