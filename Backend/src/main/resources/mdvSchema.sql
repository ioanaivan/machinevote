CREATE TABLE `electeur` (
	`idElecteur` VARCHAR(200) NOT NULL DEFAULT '',
	`Nom` VARCHAR(45) NOT NULL,
	`Prenom` VARCHAR(45) NOT NULL,
	`Commune` VARCHAR(45) NOT NULL,
	`CarteID` VARCHAR(45) NOT NULL,
	`SecuID` VARCHAR(45) NOT NULL,
	`Code` VARCHAR(20) NOT NULL,
	`CreatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`idElecteur`),
	INDEX `Code` (`Code`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

CREATE TABLE `actions` (
  `idActions` varchar(40) NOT NULL,
  `Type` varchar(40),
  `Statut` varchar(40),
  `Erreur` set('Identifiants incorrects','Vote complété avec succès'),
  `idElecteur`varchar(40) NOT NULL,
  `SecuID` varchar(40),
  PRIMARY KEY (`idActions`),
  CONSTRAINT `idElecteur` FOREIGN KEY (`idElecteur`) REFERENCES `electeur` (`idElecteur`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vote` (
	`voteId` INT(11) NOT NULL AUTO_INCREMENT,
	`voterId` VARCHAR(200) NOT NULL,
	`voteDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`optionVote` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`voteId`),
	INDEX `FK_vi` (`voterId`),
	CONSTRAINT `FK_vi` FOREIGN KEY (`voterId`) REFERENCES `electeur` (`idElecteur`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=15
;
