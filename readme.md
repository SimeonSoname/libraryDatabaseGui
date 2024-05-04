# Read Me - Init Setup


<details><summary>Expand for SQL Script</summary>

```sql
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`usertypeprivileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`usertypeprivileges` (
  `UserType` VARCHAR(50) NOT NULL,
  `CheckOutLimit` INT NULL DEFAULT NULL,
  `MaxCheckOutDuration` INT NULL DEFAULT NULL,
  PRIMARY KEY (`UserType`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`user` (
  `UTD_ID` VARCHAR(10) NOT NULL,
  `First_Name` VARCHAR(255) NULL DEFAULT NULL,
  `Middle_Name` VARCHAR(255) NULL DEFAULT NULL,
  `Last_Name` VARCHAR(255) NULL DEFAULT NULL,
  `NetID` VARCHAR(10) NULL DEFAULT NULL,
  `Email_Address` VARCHAR(255) NULL DEFAULT NULL,
  `Gender` CHAR(1) NULL DEFAULT NULL,
  `Department` VARCHAR(100) NULL DEFAULT NULL,
  `UserType` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`UTD_ID`),
  INDEX `fk_UserType` (`UserType` ASC) VISIBLE,
  CONSTRAINT `fk_UserType`
    FOREIGN KEY (`UserType`)
    REFERENCES `library`.`usertypeprivileges` (`UserType`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`address` (
  `UTD_ID` VARCHAR(10) NOT NULL,
  `Street_Address` VARCHAR(255) NULL DEFAULT NULL,
  `City` VARCHAR(100) NULL DEFAULT NULL,
  `State` VARCHAR(50) NULL DEFAULT NULL,
  `Zip_Code` VARCHAR(20) NULL DEFAULT NULL,
  `Country` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`UTD_ID`),
  CONSTRAINT `address_ibfk_1`
    FOREIGN KEY (`UTD_ID`)
    REFERENCES `library`.`user` (`UTD_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`publication` (
  `library_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `edition` VARCHAR(50) NULL DEFAULT NULL,
  `publication_date` DATE NULL DEFAULT NULL,
  `item_type` VARCHAR(50) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `available_copies` INT NULL DEFAULT NULL,
  `per_day_late_fee` DECIMAL(10,0) NULL DEFAULT NULL,
  PRIMARY KEY (`library_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`authors` (
  `Author_Name` VARCHAR(255) NULL DEFAULT NULL,
  `library_id` INT NULL DEFAULT NULL,
  INDEX `fk_author_library_id` (`library_id` ASC) VISIBLE,
  CONSTRAINT `fk_author_library_id`
    FOREIGN KEY (`library_id`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book` (
  `max_fine` DECIMAL(10,2) NULL DEFAULT NULL,
  `loc_number` VARCHAR(12) NULL DEFAULT NULL,
  `isbn` CHAR(13) NULL DEFAULT NULL,
  `num_pages` INT NULL DEFAULT NULL,
  `library_id` INT NULL DEFAULT NULL,
  `book_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`book_id`),
  INDEX `library_id` (`library_id` ASC) VISIBLE,
  CONSTRAINT `book_ibfk_1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`cd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`cd` (
  `number_of_tracks` INT NULL DEFAULT NULL,
  `CD_genre` VARCHAR(50) NULL DEFAULT NULL,
  `library_id` INT NULL DEFAULT NULL,
  INDEX `library_id` (`library_id` ASC) VISIBLE,
  CONSTRAINT `cd_ibfk_1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`ebook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`ebook` (
  `loc_number` VARCHAR(50) NULL DEFAULT NULL,
  `num_pages` INT NULL DEFAULT NULL,
  `ISBN` VARCHAR(20) NULL DEFAULT NULL,
  `library_id` INT NULL DEFAULT NULL,
  INDEX `library_id` (`library_id` ASC) VISIBLE,
  CONSTRAINT `ebook_ibfk_1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`genre` (
  `book_id` INT NULL DEFAULT NULL,
  `genre` VARCHAR(10) NULL DEFAULT NULL,
  `dewey_decimal` VARCHAR(10) NULL DEFAULT NULL,
  INDEX `book_id` (`book_id` ASC) VISIBLE,
  CONSTRAINT `genre_ibfk_1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`book_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`magazine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`magazine` (
  `library_id` INT NOT NULL,
  `max_fine` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`library_id`),
  CONSTRAINT `magazine_ibfk_1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`phonenumber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`phonenumber` (
  `UTD_ID` VARCHAR(10) NOT NULL,
  `Phone_Type` VARCHAR(50) NULL DEFAULT NULL,
  `Phone_Number` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`UTD_ID`),
  CONSTRAINT `phonenumber_ibfk_1`
    FOREIGN KEY (`UTD_ID`)
    REFERENCES `library`.`user` (`UTD_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`publisher` (
  `publisher_name` VARCHAR(255) NULL DEFAULT NULL,
  `library_id` INT NULL DEFAULT NULL,
  INDEX `library_id` (`library_id` ASC) VISIBLE,
  CONSTRAINT `publisher_ibfk_1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library`.`usercheckout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`usercheckout` (
  `Checkout_ID` INT NOT NULL AUTO_INCREMENT,
  `User_UTD_ID` CHAR(10) NULL DEFAULT NULL,
  `Library_ID` INT NULL DEFAULT NULL,
  `Checkout_Date` DATE NULL DEFAULT NULL,
  `Return_Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Checkout_ID`),
  INDEX `User_UTD_ID` (`User_UTD_ID` ASC) VISIBLE,
  INDEX `Library_ID` (`Library_ID` ASC) VISIBLE,
  CONSTRAINT `usercheckout_ibfk_1`
    FOREIGN KEY (`User_UTD_ID`)
    REFERENCES `library`.`user` (`UTD_ID`),
  CONSTRAINT `usercheckout_ibfk_2`
    FOREIGN KEY (`Library_ID`)
    REFERENCES `library`.`publication` (`library_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

```

</details>

### Key Notes

1. Add MySQL Connector for Java in classpath (8.0)
2. I used intellij