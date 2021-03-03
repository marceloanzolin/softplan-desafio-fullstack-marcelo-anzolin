
--insere os usuários
Insert into  processos.usuario (codusuario, nmusuario, emailusuario, senhausuario, tpusuario) values (2,'Desafio Softplan Admin','softadmin@gmail.com','123','A');
Insert into  processos.usuario (codusuario, nmusuario, emailusuario, senhausuario, tpusuario) values (3,'Desafio Softplan Triador','softriador@gmail.com','123','T');
Insert into  processos.usuario (codusuario, nmusuario, emailusuario, senhausuario, tpusuario) values (4,'Desafio Softplan Finalizador','softfinalizador@gmail.com','123','F');
Insert into  processos.usuario (codusuario, nmusuario, emailusuario, senhausuario, tpusuario) values (6,'Desafio Softplan Finalizador',' 2	finalizador2@gmail.com','12356','F');


--insere processos
Insert into  processos.processo (codprocesso, nomeprocesso, descricaoprocesso) values (2,'Processo 2','Descrição do processo 2');
Insert into  processos.processo (codprocesso, nomeprocesso, descricaoprocesso) values (3,'Processo 3','Descrição do processo 3');
Insert into  processos.processo (codprocesso, nomeprocesso, descricaoprocesso) values (4,'Processo 4','Descrição do processo 4');
Insert into  processos.processo (codprocesso, nomeprocesso, descricaoprocesso) values (5,'Processo 5','Descrição do processo 5');
Insert into  processos.processo (codprocesso, nomeprocesso, descricaoprocesso) values (6,'Processo 6','Descrição do processo 6');
Insert into  processos.processo (codprocesso, nomeprocesso, descricaoprocesso) values (7,'Processo 7','Descrição do processo 7');

--insere processos do usuário

Insert into	processos.processousuario (codprocesso, codusuariofinalizador, codusuariotriador, parecerprocesso, statusprocesso) values (3,4,3,null,'P');
Insert into	processos.processousuario (codprocesso, codusuariofinalizador, codusuariotriador, parecerprocesso, statusprocesso) values (4,4,3,null,'P');
Insert into	processos.processousuario (codprocesso, codusuariofinalizador, codusuariotriador, parecerprocesso, statusprocesso) values (5,6,3,null,'P');
Insert into	processos.processousuario (codprocesso, codusuariofinalizador, codusuariotriador, parecerprocesso, statusprocesso) values (7,6,3,null,'P');
Insert into	processos.processousuario (codprocesso, codusuariofinalizador, codusuariotriador, parecerprocesso, statusprocesso) values (6,6,3,'Processo 6 para o finalizado 6 com parecer certo','F');
Insert into	processos.processousuario (codprocesso, codusuariofinalizador, codusuariotriador, parecerprocesso, statusprocesso) values (2,4,3,'Processo 2 do finalizador 4 com parecer','F');

--incrementa as sequences processos do usuário
SELECT pg_catalog.setval('processos.processo_codprocesso_seq', 7, true);
SELECT pg_catalog.setval('processos.usuario_codusuario_seq', 6, true);

