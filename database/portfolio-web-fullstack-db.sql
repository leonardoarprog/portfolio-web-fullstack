-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema portfolio-web
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema portfolio-web
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portfolio-web` DEFAULT CHARACTER SET utf8 ;
USE `portfolio-web` ;

-- -----------------------------------------------------
-- Table `portfolio-web`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`usuario` (
  `id` INT NOT NULL,
  `nombre_usuario` VARCHAR(25) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_usuario_UNIQUE` (`nombre_usuario` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`pais` (
  `id` INT NOT NULL,
  `nombre_pais` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre_pais` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`provincia` (
  `id` INT NOT NULL,
  `nombre_provincia` VARCHAR(45) NOT NULL,
  `pais_id` INT NOT NULL,
  PRIMARY KEY (`id`, `pais_id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre_provincia` ASC),
  INDEX `fk_provincia_pais1_idx` (`pais_id` ASC),
  CONSTRAINT `fk_provincia_pais1`
    FOREIGN KEY (`pais_id`)
    REFERENCES `portfolio-web`.`pais` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`localidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`localidad` (
  `id` INT NOT NULL,
  `nombre_localidad` VARCHAR(45) NOT NULL,
  `codigo_postal` SMALLINT(6) NOT NULL,
  `provincia_id` INT NOT NULL,
  PRIMARY KEY (`id`, `provincia_id`),
  UNIQUE INDEX `codigo_postal_UNIQUE` (`codigo_postal` ASC),
  INDEX `fk_localidad_provincia1_idx` (`provincia_id` ASC),
  CONSTRAINT `fk_localidad_provincia1`
    FOREIGN KEY (`provincia_id`)
    REFERENCES `portfolio-web`.`provincia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`persona` (
  `id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `localidad_id` INT NOT NULL,
  `posicion_laboral` VARCHAR(45) NOT NULL,
  `sobre_mi` VARCHAR(200) NOT NULL,
  `url_foto` VARCHAR(100) NOT NULL,
  `url_img_bg` VARCHAR(100) NOT NULL,
  `url_repositorio` VARCHAR(100) NULL,
  `url_facebook` VARCHAR(100) NULL,
  `url_twitter` VARCHAR(100) NULL,
  PRIMARY KEY (`id`, `usuario_id`, `localidad_id`),
  INDEX `fk_persona_usuario1_idx` (`usuario_id` ASC),
  INDEX `fk_persona_localidad1_idx` (`localidad_id` ASC),
  CONSTRAINT `fk_persona_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `portfolio-web`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_localidad1`
    FOREIGN KEY (`localidad_id`)
    REFERENCES `portfolio-web`.`localidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`tipo_empleo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`tipo_empleo` (
  `id` INT NOT NULL,
  `nombre_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`experiencia_laboral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`experiencia_laboral` (
  `id` INT NOT NULL,
  `persona_id` INT NOT NULL,
  `tipo_empleo_id` INT NOT NULL,
  `nombre_empresa` VARCHAR(45) NOT NULL,
  `es_trabajo_actual` TINYINT NOT NULL,
  `fecha_ingreso` DATE NOT NULL,
  `fecha_egreso` DATE NOT NULL,
  `puesto` VARCHAR(75) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `url_isologo_empresa` VARCHAR(100) NULL,
  PRIMARY KEY (`id`, `persona_id`, `tipo_empleo_id`),
  INDEX `fk_experiencia_laboral_persona1_idx` (`persona_id` ASC),
  INDEX `fk_experiencia_laboral_tipo_empleo1_idx` (`tipo_empleo_id` ASC),
  CONSTRAINT `fk_experiencia_laboral_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `portfolio-web`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_experiencia_laboral_tipo_empleo1`
    FOREIGN KEY (`tipo_empleo_id`)
    REFERENCES `portfolio-web`.`tipo_empleo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`tipo_educacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`tipo_educacion` (
  `id` INT NOT NULL,
  `nombre_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`educacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`educacion` (
  `id` INT NOT NULL,
  `persona_id` INT NOT NULL,
  `tipo_educacion_id` INT NOT NULL,
  `nombre_establecimiento` VARCHAR(45) NOT NULL,
  `estoy_cursando_actualmente` TINYINT NOT NULL,
  `fecha_ingreso` DATE NOT NULL,
  `fecha_egreso` DATE NOT NULL,
  `titulo_obtenido` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(150) NULL,
  `url_isolologo_establecimiento` VARCHAR(100) NULL,
  PRIMARY KEY (`id`, `persona_id`, `tipo_educacion_id`),
  INDEX `fk_educacion_persona1_idx` (`persona_id` ASC),
  INDEX `fk_educacion_tipo_educacion1_idx` (`tipo_educacion_id` ASC),
  CONSTRAINT `fk_educacion_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `portfolio-web`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_educacion_tipo_educacion1`
    FOREIGN KEY (`tipo_educacion_id`)
    REFERENCES `portfolio-web`.`tipo_educacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`proyecto` (
  `id` INT NOT NULL,
  `persona_id` INT NOT NULL,
  `nombre_proyecto` VARCHAR(45) NOT NULL,
  `fecha_realizacion` DATE NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `url_proyecto` VARCHAR(100) NULL,
  `url_img_proyecto_1` VARCHAR(100) NULL,
  `url_img_proyecto_2` VARCHAR(100) NULL,
  `url_img_proyecto_3` VARCHAR(100) NULL,
  PRIMARY KEY (`id`, `persona_id`),
  INDEX `fk_proyecto_persona_idx` (`persona_id` ASC),
  CONSTRAINT `fk_proyecto_persona`
    FOREIGN KEY (`persona_id`)
    REFERENCES `portfolio-web`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`categoria_habilidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`categoria_habilidad` (
  `id` INT NOT NULL,
  `nombre_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`habilidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`habilidad` (
  `id` INT NOT NULL,
  `categoria_habilidad_id` INT NOT NULL,
  `nombre_habilidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `categoria_habilidad_id`),
  INDEX `fk_habilidad_categoria_habilidad1_idx` (`categoria_habilidad_id` ASC),
  CONSTRAINT `fk_habilidad_categoria_habilidad1`
    FOREIGN KEY (`categoria_habilidad_id`)
    REFERENCES `portfolio-web`.`categoria_habilidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio-web`.`persona_habilidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portfolio-web`.`persona_habilidad` (
  `persona_id` INT NOT NULL,
  `habilidad_id` INT NOT NULL,
  `score_habilidad` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`persona_id`, `habilidad_id`),
  INDEX `fk_persona_has_habilidad_habilidad1_idx` (`habilidad_id` ASC),
  INDEX `fk_persona_has_habilidad_persona1_idx` (`persona_id` ASC),
  CONSTRAINT `fk_persona_has_habilidad_persona1`
    FOREIGN KEY (`persona_id`)
    REFERENCES `portfolio-web`.`persona` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persona_has_habilidad_habilidad1`
    FOREIGN KEY (`habilidad_id`)
    REFERENCES `portfolio-web`.`habilidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
