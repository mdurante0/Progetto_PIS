-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: progetto_pis
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `magazzino_has_prodotto`
--

DROP TABLE IF EXISTS `magazzino_has_prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `magazzino_has_prodotto` (
  `magazzino_idmagazzino` int NOT NULL,
  `prodotto_articolo_idarticolo` int NOT NULL,
  `collocazione_idcollocazione` int NOT NULL,
  `quantita_prodotto` int NOT NULL,
  PRIMARY KEY (`magazzino_idmagazzino`,`prodotto_articolo_idarticolo`,`collocazione_idcollocazione`),
  KEY `fk_magazzino_has_prodotto_magazzino1_idx` (`magazzino_idmagazzino`),
  KEY `fk_magazzino_has_prodotto_collocazione1_idx` (`collocazione_idcollocazione`),
  KEY `fk_magazzino_has_prodotto_prodotto_idx` (`prodotto_articolo_idarticolo`),
  CONSTRAINT `fk_magazzino_has_prodotto_collocazione` FOREIGN KEY (`collocazione_idcollocazione`) REFERENCES `collocazione` (`idcollocazione`),
  CONSTRAINT `fk_magazzino_has_prodotto_magazzino` FOREIGN KEY (`magazzino_idmagazzino`) REFERENCES `magazzino` (`idmagazzino`),
  CONSTRAINT `fk_magazzino_has_prodotto_prodotto` FOREIGN KEY (`prodotto_articolo_idarticolo`) REFERENCES `prodotto` (`articolo_idarticolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magazzino_has_prodotto`
--

LOCK TABLES `magazzino_has_prodotto` WRITE;
/*!40000 ALTER TABLE `magazzino_has_prodotto` DISABLE KEYS */;
/*!40000 ALTER TABLE `magazzino_has_prodotto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-05 13:21:33
