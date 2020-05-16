-- CRIACAO DA SCHEMA
CREATE SCHEMA `detroit` ;


-- CRIACAO DAS TABELAS
CREATE TABLE `detroit`.`projetos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` TEXT NOT NULL,
  `estimativa` DATE NOT NULL,
  `coordenador_id` INT NOT NULL,
  `situacao_id` INT NOT NULL,
  `objetivo_id` INT NOT NULL,
  `departamento` VARCHAR(100) NULL,
  `resultado_esperado` TEXT NULL,
  `publico_beneficiario` TEXT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`objetivos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`comentarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comentario` VARCHAR(100) NOT NULL,
  `projeto_id` INT NOT NULL,
  `colaborador_id` INT NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`contribuintes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `projeto_id` INT NOT NULL,
  `papel_id` INT NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`papeis` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `papel` VARCHAR(50) NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`tarefas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `projeto_id` INT NOT NULL,
  `situacao_id` INT NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);



CREATE TABLE `detroit`.`contribuintes_tarefas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contribuinte_id` INT NOT NULL,
  `tarefa_id` INT NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT now(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`colaboradores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matricula` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `data_cadastro` datetime NOT NULL DEFAULT NOW(),
  `ativo` bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id` ASC),
  UNIQUE KEY `email_UNIQUE` (`email` ASC),
  UNIQUE KEY `matricula_UNIQUE` (`matricula` ASC)
);


CREATE TABLE `detroit`.`situacoes_projetos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `situacao` VARCHAR(50) NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


CREATE TABLE `detroit`.`situacoes_tarefas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `situacao` VARCHAR(50) NOT NULL,
  `data_cadastro` DATETIME NOT NULL DEFAULT NOW(),
  `ativo` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);


-- INSERINDO OS VALORES
INSERT INTO `detroit`.`situacoes_projetos` (`situacao`) VALUES ('Ativo');
INSERT INTO `detroit`.`situacoes_projetos` (`situacao`) VALUES ('Inativo');
INSERT INTO `detroit`.`situacoes_projetos` (`situacao`) VALUES ('Completo');

INSERT INTO `detroit`.`situacoes_tarefas` (`situacao`) VALUES ('ToDo');
INSERT INTO `detroit`.`situacoes_tarefas` (`situacao`) VALUES ('Doing');
INSERT INTO `detroit`.`situacoes_tarefas` (`situacao`) VALUES ('Done');


INSERT INTO `detroit`.`colaboradores` (`matricula`, `nome`, `email`, `senha`) VALUES ('1', 'Leonardo Gil', 'leogilc@gmail.com', '123');
INSERT INTO `detroit`.`colaboradores` (`matricula`, `nome`, `email`, `senha`) VALUES ('2', 'Jonathan Araujo', 'teste@gmail.com', '123');
INSERT INTO `detroit`.`colaboradores` (`matricula`, `nome`, `email`, `senha`) VALUES ('3', 'Alexandre Boschi', 'teste2@gmail.com', '123');
INSERT INTO `detroit`.`colaboradores` (`matricula`, `nome`, `email`, `senha`) VALUES ('4', 'William Ribeiro', 'teste3@gmail.com', '123');


INSERT INTO `detroit`.`objetivos` (`descricao`) VALUES ('Melhoria de Processo');
INSERT INTO `detroit`.`objetivos` (`descricao`) VALUES ('Social');


INSERT INTO `detroit`.`projetos` (`nome`, `descricao`, `estimativa`, `coordenador_id`, `situacao_id`, `objetivo_id`, `departamento`, `resultado_esperado`) VALUES ('Projeto melhoria de processo', 'Teste de projeto', '2020-06-03', '1', '1', '1', 'Departamento teste', 'Melhorar o processo X');
INSERT INTO `detroit`.`projetos` (`nome`, `descricao`, `estimativa`, `coordenador_id`, `situacao_id`, `objetivo_id`, `publico_beneficiario`) VALUES ('Projeto Social', 'Teste desse aqui tambem', '2020-06-03', '2', '1', '2', 'O pessoal ali');
