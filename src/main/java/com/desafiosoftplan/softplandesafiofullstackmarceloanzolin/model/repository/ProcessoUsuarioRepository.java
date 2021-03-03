package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoId;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoUsuario;

public interface ProcessoUsuarioRepository extends JpaRepository<ProcessoUsuario, ProcessoId>{

	@Query(value = "SELECT * FROM processos.processousuario WHERE codprocesso = :codprocesso and codusuariofinalizador = :codusuariofinalizador", nativeQuery = true)
	Optional<ProcessoUsuario> findByProcessoUsuarioCustom(@Param("codprocesso") Long codProcesso,@Param("codusuariofinalizador") Long codUsuarioFinalizador);
	
	@Query(value = "SELECT * FROM processos.processousuario    WHERE  codusuariofinalizador = :codusuariofinalizador and statusprocesso = :tpstatus ", nativeQuery = true)
	List<ProcessoUsuario> findByProcessoUsuarioStatusCustom(@Param("codusuariofinalizador") Long codUsuarioFinalizador,@Param("tpstatus") String tpStatus);

	List<ProcessoUsuario> findAll();

}
