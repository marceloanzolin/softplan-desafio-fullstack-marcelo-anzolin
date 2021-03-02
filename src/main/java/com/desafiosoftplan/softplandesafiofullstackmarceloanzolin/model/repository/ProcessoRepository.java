package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Processo;
import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.ProcessoUsuario;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	
	List<Processo> findAll();

}
