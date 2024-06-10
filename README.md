# Projet Country Search

## Description du Projet
L'application permet aux utilisateurs de rechercher des informations sur les pays, d'afficher les résultats de la recherche sous forme de liste, de visualiser les fiches des pays et de mettre certains pays en favoris pour les consulter sans connexion.  Cette application est développé dans le cadre du cours **matériel mobile** de 4e année à l'[EPF](https://www.epf.fr/).

## Fonctionnalités Principales
1. **Recherche de Pays** :  Permet de rechercher un pays via une chaîne de caractère correspondant à une partie du nom de la capitale ou du nom du pays.
2. **Affichage des Résultats** :  Affiche les résultats de la recherche sous forme de liste incluant les drapeaux et les noms des pays.
3. **Fiche d’un Pays** :  Permet d'afficher les détails d'un pays sélectionné depuis la liste des résultats.
4. **Favoris** :  Permet de mettre certains pays en favoris afin de pouvoir les consulter sans connexion Internet.

## Fonctionnalités Suplémentaires
* **Map du pays** : Affichage de la carte du pays dans la fiche du pays

## Liens Utiles
* [Rest Countries API](https://restcountries.com/)
* [Android Developers](https://developer.android.com)
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://bumptech.github.io/glide)

## Installation et Utilisation

* Cloner le dépôt du projet :  `git clone https://github.com/mab1o/country-search-app.git`
* Ouvrir le projet dans Android Studio :  Aller dans File -> Open et sélectionner le dossier du projet cloné.
* Lancer l’application :  Connecter un appareil Android ou utiliser un émulateur; Cliquer sur le bouton Run dans Android Studio.

## Documentation Utilisateur
* Recherche de Pays
    * Dans la barre de recherche de l'application, entrez une partie du nom d'un pays ou de sa capital.
    * Il faut par la suite selectionner "by Capital" ou "by Country" et appuyer sur search.
    * Les résultats s'afficheront sous forme de liste avec les drapeaux et les noms des pays correspondants.
* Affichage des Fiches de Pays
    * Cliquez sur un pays dans la liste des résultats pour voir les détails complets de ce pays.
* Ajout aux Favoris
    * Sur la fiche détaillée d'un pays, appuyez sur l'icône en forme d'étoile pour ajouter le pays à vos favoris.
    * Vous pouvez consulter vos pays favoris depuis la section Favoris de l'application, même sans connexion Internet.