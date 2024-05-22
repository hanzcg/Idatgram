create schema `demo_db`;

use `demo_db`;

CREATE TABLE `usuarios` (
  `id` int NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `last_name` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);