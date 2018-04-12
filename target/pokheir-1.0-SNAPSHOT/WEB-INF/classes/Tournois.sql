CREATE TABLE tournois (
  idTournois INT NOT NULL AUTO_INCREMENT,
  date DATE NOT NULL,
  nombreInscrits INT NOT NULL,
  classe BOOLEAN NOT NULL,
  PRIMARY KEY (idTournois)
);