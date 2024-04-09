# Cahier technique

## A quoi sert le projet / pour qui ?
Le projet vise à développer une application de location de voitures en JavaFX. Les utilisateurs pourront choisir parmi 10 agences pour la prise en charge et la restitution des véhicules. Il sera possible de gérer la flotte automobile, les réservations et les clients.
Ce projet est destiné uniquement aux employés de l'entreprise de location de voitures. (logiciel de gestion)

## Gantt
![GANT.png](GANT.png)  

## Contraintes techniques
- Java : version 11
- JavaFX : version 11
- Base de données MySQL : version 8.0
- Jira : https://nihoncollection.atlassian.net/jira/software/projects/THACK/boards/4?atlOrigin=eyJpIjoiMmM1ZWJiMmZjNmE0NGUxZmFlOTVjMjI1Mjk0YWFiOGEiLCJwIjoiaiJ9
- Figma
- GitHub
- Discord

## Structure du projet
*packages, a quoi ils servent etc...  
(DAO, MWA, Controller, etc.)*

* **dao**: Data Access Object, contient les classes qui permettent de faire le lien entre la base de données et l'application.
* **mwa**: Motorized Wide Area, contient les classes qui permettent de gérer les objets métiers.
* **controller**: contient les classes qui permettent de gérer les événements de l'interface graphique.
* **test**: contient les classes qui permettent de tester les différentes fonctionnalités de l'application.
* **resources**: contient les fichiers de ressources (images, css, etc.)


## Modèle relationnel

**User** (**id** : int(7), **nom** : varchar(100), prenom : varchar(100), role : enum(...))  
**Clé primaire** : id (auto-incrémenté)  
**Clé étrangère** : /

**Client** (**id** : int(7), **nom** : varchar(100), **prenom** : varchar(100), email : varchar(100), dateNaiss : Date, telephone : varchar(10))  
**Clé primaire** : id (auto-incrémenté)  
**Clé étrangère** : /

**Reservation** (**id** : int(7), **idClient** : int(7), **idVoiture** : int(7) ,**dateDebut** : Date, **dateFin** : Date, **prixTotal** : int, **agenceDebut** : enum(Agence), **agenceFin** : enum(Agence), **incident** : Boolean)  
**Clé primaire** : id (auto-incrémenté)  
**Clé étrangère** :
idClient -> Client.id
idVoiture -> Voiture.id

**Voiture** (**id** : int(7), **marque** : varchar(100), **modele** : varchar(100), **immatriculation** varchar(10),**typeVehicule** : enum(...), **statutVehicule** : enum(...), **agence** : enum(...), **prix** : float(7,2))  
**Clé primaire** : id (auto-incrémenté)  
**Clé étrangère** : /


### Rappel du diagramme de classe UML
![UML](UML.png)

## Fichier SQL pour la BDD
Le fichier SQL pour la base de données est disponible ici :  
[DB.sql](MotorizedWideArea_App/DB.sql)
