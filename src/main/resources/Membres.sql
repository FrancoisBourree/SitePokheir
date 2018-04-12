# Dans un premier temps

CREATE DATABASE base;

# Puis dans la base

CREATE TABLE membre (
  email VARCHAR(42) NOT NULL,
  nom VARCHAR(42) NOT NULL,
  prenom VARCHAR(42) NOT NULL,
  classe VARCHAR(42) NOT NULL,
  mdp VARCHAR(42) NOT NULL,
  nbPoints INT NOT NULL,
  partiesGagnees INT NOT NULL,
  partiesJouees INT NOT NULL,
  participe BOOLEAN,
  PRIMARY KEY (email)
);

# Et enfin dans membre

INSERT INTO membre VALUES ('admin@hei.yncrea.fr','admin','admin','H00','hei2018',0,0,0)