
--Cria a base 
CREATE DATABASE gprocessosoftplan
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
--Cria o schema	
CREATE SCHEMA processos;


CREATE SEQUENCE processos.processo_codprocesso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


CREATE TABLE processos.processo (
    codprocesso bigint DEFAULT nextval('processos.processo_codprocesso_seq'::regclass) NOT NULL,
    nomeprocesso character varying(50) NOT NULL,
    descricaoprocesso character varying(200) NOT NULL
);


ALTER TABLE processos.processo OWNER TO postgres;

COMMENT ON TABLE processos.processo IS 'Tabela que armazena os processos cadastrados para o sistema';

CREATE TABLE processos.processousuario (
    codprocesso bigint NOT NULL,
    codusuariofinalizador bigint NOT NULL,
    codusuariotriador bigint NOT NULL,
    parecerprocesso character varying(2000),
    statusprocesso "char" DEFAULT 'P'::"char",
    CONSTRAINT proceusu_tpstatusprocessousu_check CHECK (((statusprocesso)::text = ANY (ARRAY[('P'::character varying)::text, ('F'::character varying)::text])))
);


COMMENT ON TABLE processos.processousuario IS 'Tabela que armazena os processos que estão vinculados aos usuários designados para informar o parecer ao processo
Possui chave composta formado pelo codprocesso e codusuariofinalizador';


COMMENT ON COLUMN processos.processousuario.statusprocesso IS 'Armazena o status do processo podendo ser:
P -> Pendente = Pendente de parecer no momento da vinculação do processo com um usuário
F -> Finalizado = Quando o usuário informou parecer para o processo';


CREATE SEQUENCE processos.usuario_codusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999
    CACHE 1;

CREATE TABLE processos.usuario (
    codusuario bigint DEFAULT nextval('processos.usuario_codusuario_seq'::regclass) NOT NULL,
    nmusuario character varying(50) NOT NULL,
    emailusuario character varying(80) NOT NULL,
    senhausuario character varying(10) NOT NULL,
    tpusuario character(1) NOT NULL,
    CONSTRAINT usuario_tpusuario_check CHECK (((tpusuario)::text = ANY (ARRAY[('A'::character varying)::text, ('F'::character varying)::text, ('T'::character varying)::text])))
);


COMMENT ON TABLE processos.usuario IS 'Tabela que irá armazenar todos os usuários do sistema podendo ser do tipo 
A -> Administrador
T -> Triador
F -> Finalizador';

COMMENT ON COLUMN processos.usuario.codusuario IS 'Chave primária incrementada pela sequence usuario_codusuario_seq';

COMMENT ON COLUMN processos.usuario.tpusuario IS 'Armazena o tipo do Usuário
A -> Administrador
F -> Finalizador
T - > Triador
Validado pela check usuario_tpusuario_check';


ALTER TABLE ONLY processos.processousuario
    ADD CONSTRAINT codprocesso_pkey PRIMARY KEY (codprocesso, codusuariofinalizador);

ALTER TABLE ONLY processos.processo
    ADD CONSTRAINT processo_pkey PRIMARY KEY (codprocesso);

ALTER TABLE ONLY processos.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (codusuario);

ALTER TABLE ONLY processos.processousuario
    ADD CONSTRAINT processousuario_codprocesso_fkey FOREIGN KEY (codprocesso) REFERENCES processos.processo(codprocesso);

ALTER TABLE ONLY processos.processousuario
    ADD CONSTRAINT processousuario_usuariofinalizador_fkey FOREIGN KEY (codusuariofinalizador) REFERENCES processos.usuario(codusuario);

ALTER TABLE ONLY processos.processousuario
    ADD CONSTRAINT processousuario_usuariotriador_fkey FOREIGN KEY (codusuariotriador) REFERENCES processos.usuario(codusuario);