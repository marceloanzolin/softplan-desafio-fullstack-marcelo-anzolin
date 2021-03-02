package com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums;

/**
 * Tipos para os tipos de Usuários possiveis entidade {@see Usuario.java}
 * A = Administrador -> Permissão para cirar Usuários
 * F = Finalizador -> Permissão para inserir parecer nos processos
 * T = Triador -> Permissão para Atribuir usuários finalizadores ao processo
 * 
 * @author Marcelo Anzolin
 * @version 1.0
 * @see com.desafiosoftplan.softplandesafiofullstackmarceloanzolin.model.enums
 */
public enum TipoUsuario {
 A,
 F,
 T
}
