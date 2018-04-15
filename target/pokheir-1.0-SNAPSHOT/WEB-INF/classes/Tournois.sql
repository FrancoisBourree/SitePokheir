CREATE TABLE tournois (
  idTournois INT NOT NULL AUTO_INCREMENT,
  date TEXT NOT NULL,
  nombreInscrits INT NOT NULL,
  classe BOOLEAN NOT NULL,
  PRIMARY KEY (idTournois)
);