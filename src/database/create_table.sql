CREATE TABLE `elokuva` (
  `elokuvaID` int(20) NOT NULL AUTO_INCREMENT,
  `elokuvaNimi` varchar(50) NOT NULL,
  PRIMARY KEY (elokuvaID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `kommentti` (
  `kommenttiID` int(20) NOT NULL AUTO_INCREMENT,
  `elokuvaID` int(20) NOT NULL,
  `kommenttiTeksti` varchar(200) NOT NULL,
  PRIMARY KEY (kommenttiID),
  FOREIGN KEY (elokuvaID) REFERENCES elokuva(elokuvaID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;