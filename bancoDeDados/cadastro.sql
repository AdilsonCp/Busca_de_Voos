drop database if exists cadastro;
create database cadastro;
use cadastro;


CREATE TABLE IF NOT EXISTS `cadastro`.`pessoas` (
  `pessoa_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `genero` VARCHAR(20) NOT NULL,
  `data_nascimento` DATETIME NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pessoa_id`),
  UNIQUE INDEX `idx_cpf` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `cadastro`.`companhias_aerea` (
  `id_companhias` INT NOT NULL AUTO_INCREMENT,
  `nome_companhia` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_companhias`),
  UNIQUE INDEX `idx_companhias_aerea` (`nome_companhia` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `cadastro`.`paises` (
  `id_paises` INT NOT NULL AUTO_INCREMENT,
  `pais` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_paises`),
  UNIQUE INDEX `idx_pais` (`pais` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `cadastro`.`cidades` (
  `id_cidades` INT NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(100) NOT NULL,
  `paises_id_paises` INT NOT NULL,
  PRIMARY KEY (`id_cidades`, `paises_id_paises`),
  UNIQUE INDEX `idx_cidade` (`cidade` ASC) VISIBLE,
  INDEX `fk_cidades_paises1_idx` (`paises_id_paises` ASC) VISIBLE,
  CONSTRAINT `fk_cidades_paises1`
    FOREIGN KEY (`paises_id_paises`)
    REFERENCES `cadastro`.`paises` (`id_paises`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 113
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `cadastro`.`rota_aereas` (
  `id_rotas_aereas` INT NOT NULL AUTO_INCREMENT,
  `empresa` VARCHAR(100) NOT NULL,
  `data_horario_partida` DATETIME NOT NULL,
  `valores` DOUBLE NOT NULL,
  `cidades_origemid_cidades` INT NOT NULL,
  `paisorigem_paises_id_paises` INT NOT NULL,
  `cidadesdestino_id_cidades` INT NOT NULL,
  `paisdestino_paises_id_paises` INT NOT NULL,
  PRIMARY KEY (`id_rotas_aereas`),
  INDEX `empresa` (`empresa` ASC) VISIBLE,
  INDEX `fk_rota_aereas_cidades1_idx` (`cidades_origemid_cidades` ASC, `paisorigem_paises_id_paises` ASC) VISIBLE,
  INDEX `fk_rota_aereas_cidades2_idx` (`cidadesdestino_id_cidades` ASC, `paisdestino_paises_id_paises` ASC) VISIBLE,
  CONSTRAINT `rota_aereas_ibfk_5` FOREIGN KEY (`empresa`) REFERENCES `cadastro`.`companhias_aerea` (`nome_companhia`),
  CONSTRAINT `fk_rota_aereas_cidades1`FOREIGN KEY (`cidades_origemid_cidades` , `paisorigem_paises_id_paises`)
				REFERENCES `cadastro`.`cidades` (`id_cidades` , `paises_id_paises`)
				ON DELETE NO ACTION
				ON UPDATE NO ACTION,
  CONSTRAINT `fk_rota_aereas_cidades2`FOREIGN KEY (`cidadesdestino_id_cidades` , `paisdestino_paises_id_paises`)
				REFERENCES `cadastro`.`cidades` (`id_cidades` , `paises_id_paises`)
				ON DELETE NO ACTION
				ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4141
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;




CREATE TABLE IF NOT EXISTS `cadastro`.`poltrona` (
  `id_poltrona` VARCHAR(2) NOT NULL,
  `id_rotas_aereas` INT NOT NULL,
  `statuss` INT NULL,
  PRIMARY KEY (`id_poltrona`, `id_rotas_aereas`),
  INDEX `id_rotas_aereas` (`id_rotas_aereas` ASC) VISIBLE,
  CONSTRAINT `poltrona_ibfk_1`
    FOREIGN KEY (`id_rotas_aereas`)
    REFERENCES `cadastro`.`rota_aereas` (`id_rotas_aereas`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4141
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;




CREATE TABLE IF NOT EXISTS `cadastro`.`passagem` (
  `id_passagem` INT NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT NOT NULL,
  `poltrona_id_poltrona` VARCHAR(2) NOT NULL,
  `poltrona_id_rotas_aereas` INT NOT NULL,
  PRIMARY KEY (`id_passagem`),
  INDEX `pessoa_id` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_passagem_poltrona1_idx` (`poltrona_id_poltrona` ASC, `poltrona_id_rotas_aereas` ASC) VISIBLE,
  CONSTRAINT `passageiros_ibfk_1` FOREIGN KEY (`pessoa_id`) REFERENCES `cadastro`.`pessoas` (`pessoa_id`),
  CONSTRAINT `fk_passagem_poltrona1`FOREIGN KEY (`poltrona_id_poltrona` , `poltrona_id_rotas_aereas`) REFERENCES `cadastro`.`poltrona` (`id_poltrona` , `id_rotas_aereas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;












