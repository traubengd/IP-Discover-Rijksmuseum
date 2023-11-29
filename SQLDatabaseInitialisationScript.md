CREATE DATABASE `iprijksmuseum` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `players` (
  `playername` varchar(25) NOT NULL,
  `playerscore` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`playername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
