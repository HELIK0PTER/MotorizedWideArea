CREATE DATABASE IF NOT EXISTS motorized_wide_area;

USE motorized_wide_area;

CREATE TABLE User (
                      id INT(7) AUTO_INCREMENT PRIMARY KEY,
                      nom VARCHAR(100),
                      prenom VARCHAR(100),
                      role ENUM('ADMIN', 'GESTION_RESA_CLIENT', 'GESTION_FLOTTE')
);

CREATE TABLE Client (
                        id INT(7) AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(100),
                        prenom VARCHAR(100),
                        email VARCHAR(100),
                        dateNaiss DATE,
                        telephone VARCHAR(10)
);

CREATE TABLE Voiture (
                         id INT(7) AUTO_INCREMENT PRIMARY KEY,
                         marque VARCHAR(100),
                         modele VARCHAR(100),
                         immatriculation VARCHAR(10),
                         typeVehicule ENUM('SUV', 'MINIBUS', 'FAMILIALE', 'CITADINE', 'BERLINE', 'SPORTIVE'),
                         statutVehicule ENUM('DISPONIBLE', 'EN_MAINTENANCE', 'RESERVE'),
                         agence ENUM('1', '2', '3', '4', '5', '6', '7', '8', '9', '10'),
                         prix FLOAT(7,2)
    );

CREATE TABLE Reservation (
                             id INT(7) AUTO_INCREMENT PRIMARY KEY,
                             idClient INT(7),
                             idVoiture INT(7),
                             dateDebut DATE,
                             dateFin DATE,
                             prixTotal INT,
                             agenceDebut ENUM('1', '2', '3', '4', '5', '6', '7', '8', '9', '10'),
                             agenceFin ENUM('1', '2', '3', '4', '5', '6', '7', '8', '9', '10'),
                             incident BOOLEAN,
                             FOREIGN KEY (idClient) REFERENCES Client(id),
                             FOREIGN KEY (idVoiture) REFERENCES Voiture(id)
);

INSERT INTO User (nom, prenom, role) VALUES ('Dupont', 'Jean', 'ADMIN');
INSERT INTO User (nom, prenom, role) VALUES ('Durand', 'Pierre', 'GESTION_RESA_CLIENT');
INSERT INTO User (nom, prenom, role) VALUES ('Duchemin', 'Paul', 'GESTION_FLOTTE');

INSERT INTO Client (nom, prenom, email, dateNaiss, telephone) VALUES ('Martin', 'Jean','jean.m@gmail.com', '1990-01-01', '0601020304');
INSERT INTO Client (nom, prenom, email, dateNaiss, telephone) VALUES ('Bernard', 'Pierre','pierre.b@gmail.com', '1995-01-01', '0601020304');
INSERT INTO Client (nom, prenom, email, dateNaiss, telephone) VALUES ('Dubois', 'Paul','paul.d@gmail.com', '1990-01-01', '0601020304');

INSERT INTO Voiture (marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix) VALUES ('Peugeot', '3008', 'AB-123-CD', 'SUV', 'DISPONIBLE', '1', '50');
INSERT INTO Voiture (marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix) VALUES ('Renault', 'Espace', 'EF-456-GH', 'MINIBUS', 'DISPONIBLE', '2', '60');
INSERT INTO Voiture (marque, modele, immatriculation, typeVehicule, statutVehicule, agence, prix) VALUES ('Citroen', 'C4', 'IJ-789-KL', 'CITADINE', 'DISPONIBLE', '3', '40');

INSERT INTO Reservation (idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident) VALUES ('1', '1', '2021-01-01', '2021-01-02', '50', '1', '1', '0');
INSERT INTO Reservation (idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident) VALUES ('2', '2', '2021-01-01', '2021-01-02', '60', '2', '2', '0');
INSERT INTO Reservation (idClient, idVoiture, dateDebut, dateFin, prixTotal, agenceDebut, agenceFin, incident) VALUES ('3', '3', '2021-01-01', '2021-01-02', '40', '3', '3', '0');
