CREATE TABLE `electeur` (
  `idElecteur` varchar(40) NOT NULL,
  `Nom` varchar(40),
  `Prenom` varchar(40),
  `Commune` varchar(40),
  `CarteID` varchar(40),
  `SecuID` varchar(40),
  `Code` varchar(40),
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idElecteur`),
  UNIQUE KEY (`Nom`),
  UNIQUE KEY (`Prenom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
