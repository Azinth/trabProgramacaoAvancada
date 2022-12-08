# trabProgramacaoAvancada
Trabalho Programação Avançada 4° semestre UNIJUAZEIRO


Link do repositório: 
Descrições de configuração necessária:
  
  fazer a importação do cod na IDE NetBeans;
  Criar Schema do banco no mysql;


Schema do banco de dados:

DROP SCHEMA IF EXISTS `produtos` ;
CREATE SCHEMA IF NOT EXISTS `produtos` 
CREATE TABLE IF NOT EXISTS `produtos`.`produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `fabricacao` VARCHAR(45) NOT NULL,
  `validade` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
CREATE TABLE IF NOT EXISTS `produtos`.`usuarios` (
  `nome` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci



Informações de IDE e demais ferramentas que foram utilizadas:

  Foi usado o Apache NetBeans IDE 15 para criar e compilar o projeto;
  Runtime: Java(TM) SE Runtime Environment 18.0.2+9-61
  DB: MySQL Workbench 8.0.31

Nome da equipe do projeto.

  1. Artur Batista
  2. Ednário Andrade
  3. Gabriel Viana
