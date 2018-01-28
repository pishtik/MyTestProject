-- --------------------------------------------------------
-- Hostiteľ:                     127.0.0.1
-- Verze serveru:                10.1.9-MariaDB - mariadb.org binary distribution
-- OS serveru:                   Win32
-- HeidiSQL Verzia:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Exportování struktury databáze pro
CREATE DATABASE IF NOT EXISTS `crud_test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_slovak_ci */;
USE `crud_test`;

-- Exportování struktury pro tabulka crud_test.producttax
CREATE TABLE IF NOT EXISTS `producttax` (
  `TaxId` int(10) NOT NULL AUTO_INCREMENT,
  `TaxName` varchar(50) COLLATE utf8_slovak_ci DEFAULT NULL,
  `TaxDescription` varchar(250) COLLATE utf8_slovak_ci DEFAULT NULL,
  PRIMARY KEY (`TaxId`),
  UNIQUE KEY `TaxId` (`TaxId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

-- Exportování dat pro tabulku crud_test.producttax: ~3 rows (přibližně)
/*!40000 ALTER TABLE `producttax` DISABLE KEYS */;
INSERT INTO `producttax` (`TaxId`, `TaxName`, `TaxDescription`) VALUES
	(10, '10%', 'lower tax'),
	(20, '20%', 'normal tax'),
	(25, '25%', 'higher tax'),
	(30, '30%', 'higest tax');
/*!40000 ALTER TABLE `producttax` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
