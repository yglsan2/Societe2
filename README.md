Societe2, c'est le projet Societe (premier ECF)  corrigé. 

 Gestion des Clients et Prospects - Application Desktop en Java

## Description du Projet

Ce projet est une application desktop développée en Java pour la gestion des clients et prospects d'une entreprise. 
L'application permet de créer, modifier, supprimer et afficher les clients et prospects de manière intuitive. 
Elle est conçue avec une interface graphique utilisant la bibliothèque Swing et
respecte les principes de la Programmation Orientée Objet (POO).

### Fonctionnalités Principales

- **Gestion des Clients** :
  - Création, modification, suppression et affichage des clients.
  - Les clients possèdent des informations telles que la raison sociale, l'adresse, le chiffre d'affaires, le nombre d'employés, etc.
  
- **Gestion des Prospects** :
  - Création, modification, suppression et affichage des prospects.
  - Les prospects possèdent des informations spécifiques comme la date de prospection et leur niveau d'intérêt.

- **Interface Utilisateur** :
  - Une page d'accueil pour choisir entre la gestion des clients ou des prospects.
  - Des formulaires dynamiques pour la création, modification et suppression.
  - Une vue unifiée pour l'affichage des listes de clients ou prospects, triée par raison sociale.

- **Validation des Données** :
  - Utilisation de Regex pour valider les champs tels que le code postal, le numéro de téléphone et l'adresse email.
  - Gestion des contraintes métier (ex : raison sociale unique, chiffre d'affaires minimum, etc.).

- **Gestion des Erreurs** :
  - Utilisation de la gestion des exceptions pour une expérience utilisateur robuste.
  - Création de logs pour le suivi des activités et des erreurs.

- **Tests Unitaires** :
  - Tests unitaires implémentés avec JUnit 5 pour garantir la fiabilité du code.

---

## Structure du Projet

### Diagramme de Classes

Le diagramme de classes suit une structure hiérarchique avec une classe abstraite `Societe` comme classe mère. Les classes `Client` et `Prospect` héritent de `Societe` et ajoutent des attributs spécifiques.

Societe (abstraite)
├── Client
│ ├── chiffreAffaires
│ └── nombreEmployes
└── Prospect
├── dateProspection
└── interesse



### Packages

- **`metier`** : Contient les classes métier (`Societe`, `Client`, `Prospect`, `Adresse`).
- **`dao`** : Gère les collections de clients et prospects (`ClientDAO`, `ProspectDAO`).
- **`vue`** : Contient les interfaces graphiques (pages d'accueil, formulaires, affichage).
- **`controleur`** : Gère la logique entre la vue et le modèle.
- **`tests`** : Contient les tests unitaires.
- **`logs`** : Stocke les fichiers de logs.
- **`javadoc`** : Documentation générée avec Javadoc.

---

## Technologies Utilisées

- **Langage** : Java
- **Interface Graphique** : Swing
- **Gestion des Données** : Collections Java (`ArrayList`)
- **Validation** : Regex
- **Tests Unitaires** : JUnit 5
- **Gestion de Version** : Git (dépôt distant sur GitHub/GitLab)
- **Documentation** : Javadoc
- **Gestion des Logs** : Utilisation de `java.util.logging` ou une bibliothèque tierce comme Log4j.

---

## Installation et Exécution

### Prérequis

- JDK 21 installé.
- IDE : IntelliJ
- Git pour le contrôle de version.

### Étapes pour Exécuter le Projet

1. **Cloner le dépôt** :
   ```bash
   git clone https://github.com/yglsan2/Societe2.git [adresse_où_vous_voulez_cloner]

    Importer le projet dans votre IDE :

        Ouvrez votre IDE et importez le projet en tant que projet Maven ou Gradle (selon la configuration).

    Exécuter l'application :

        Localisez la classe principale (Main.java) et exécutez-la.

    Générer la Javadoc :
    bash : 
  

  javadoc -d javadoc -sourcepath src -subpackages metier dao vue controleur

    Exécuter les tests unitaires :

        Utilisez votre IDE ou exécutez les tests via la commande :
    bash : 
  mvn test

Documentation

    Javadoc : La documentation détaillée des classes et méthodes est disponible dans le dossier javadoc.

    Logs : Les fichiers de logs sont stockés dans le dossier logs pour le suivi des activités et des erreurs.

Aperçu de l'Interface Utilisateur
Page d'Accueil

    Choix entre gestion des clients ou des prospects.

    Options pour créer, modifier, supprimer ou afficher.

Formulaire de Création/Modification

    Champs dynamiques en fonction du type de société (client ou prospect).

    Validation des données en temps réel.

Affichage des Listes

    Tableau trié par raison sociale.

    Affichage des détails sauf les commentaires.

Contributeurs

  Yglsan2

Licence

Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.

N'hésitez pas à explorer le code et à contribuer ! 🚀




