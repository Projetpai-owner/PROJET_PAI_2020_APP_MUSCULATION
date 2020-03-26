# PROJET_PAI_2020_APP_MUSCULATION 

###### mbp-rest [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mybodypartner-rest&metric=alert_status)](https://sonarcloud.io/dashboard?id=mybodypartner-rest)

###### mbp-webapp [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Projetpai-owner_PROJET_PAI_2020_APP_MUSCULATION&metric=alert_status)](https://sonarcloud.io/dashboard?id=Projetpai-owner_PROJET_PAI_2020_APP_MUSCULATION)

### Prérequis

Ce que vous avez besoin pour installer le projet :

* Cloner le projet :  https://github.com/Projetpai-owner/PROJET_PAI_2020_APP_MUSCULATION.git
* [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [NODE JS](https://nodejs.org/dist/v12.16.1/) 
* [MAVEN](https://maven.apache.org/)
* [CURL](https://curl.haxx.se/) OU [POSTMAN](https://www.postman.com/)

### Installation
``` sh
$ cd [cheminVersLeProjet]/PROJET_PAI_2020_APP_MUSCULATION/mbp-webapp
```

Installer les dépendances du projet
``` sh
$ npm install
```

Un dossier node_modules s'est installé, contenant les dépendances du projet.
Ajouter au path la commande **ng** .

``` sh
$ export PATH = $PATH:[cheminAbsoluVersLeProjet]/PROJET_PAI_2020_APP_MUSCULATION/mbp-webapp/node_modules/@angular/cli/bin/ng
```

### Exécuter les tests

``` sh
$ cd [cheminVersLeProjet]/PROJET_PAI_2020_APP_MUSCULATION/mbp-rest-service
$ mvn test
```
### Lancement du projet

**Lancement de la wepapp** : 
```sh
$ cd [cheminVersLeProjet]/PROJET_PAI_2020_APP_MUSCULATION/mbp-webapp
$ ng serve
$ firefox localhost:4200 &
```

**Lancement de l'api REST** : 
```sh
$ cd [cheminVersLeProjet]/PROJET_PAI_2020_APP_MUSCULATION/mbp-rest-service
$ mvn clean install
$ mvn spring-boot:run [OU java -jar target/mbpRestApi.jar]
$ curl -v localhost:8080
```



