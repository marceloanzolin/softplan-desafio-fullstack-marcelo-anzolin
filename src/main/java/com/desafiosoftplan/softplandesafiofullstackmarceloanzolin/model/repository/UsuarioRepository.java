package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmailUsuario(String email);
	
	Optional<Usuario> findByEmailUsuario(String email);
	
	@Query(value = "SELECT * FROM processos.usuario WHERE UPPER(NMUSUARIO)  like UPPER(:nmUsuario)", nativeQuery = true)

	List<Usuario> findByUsuarioCustom(@Param("nmUsuario") String nmUsuario);
	
	List<Usuario> findAll();

}
