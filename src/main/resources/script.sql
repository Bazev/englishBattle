CREATE TABLE `Joueur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `estEnLigne` bit(1) DEFAULT b'0',
  `motDePasse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `niveau_id` bigint(20) DEFAULT NULL,
  `ville_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lb1s5xv65691p62nrc5t5xgje` (`email`),
  KEY `FKcpjvo1jijb7r3lkfvstn8giv9` (`niveau_id`),
  KEY `FKxxc141y7qmjjafnkggoqlnqs` (`ville_id`)
) ENGINE=InnoDB;

CREATE TABLE `Niveau` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `Partie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `joueur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt39vhifbvj2ods6y2yckro7bj` (`joueur_id`)
) ENGINE=InnoDB;

CREATE TABLE `Question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateEnvoi` datetime DEFAULT NULL,
  `dateReponse` datetime DEFAULT NULL,
  `reponseParticipePasse` varchar(255) DEFAULT NULL,
  `reponsePreterit` varchar(255) DEFAULT NULL,
  `partie_id` bigint(20) DEFAULT NULL,
  `verbe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ygknjb3g59ty3fqrpyh0dkue` (`partie_id`),
  KEY `FKhvqcqpbxbewcd19cnpec4iqlh` (`verbe_id`)
) ENGINE=InnoDB;

CREATE TABLE `Verbe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `baseVerbale` varchar(255) DEFAULT NULL,
  `participePasse` varchar(255) DEFAULT NULL,
  `preterit` varchar(255) DEFAULT NULL,
  `traduction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h0dv0b7vku0d6p51xxn0af0is` (`baseVerbale`)
) ENGINE=InnoDB;

CREATE TABLE `Ville` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
