# API Utilisateurs Français

## Description
Cette API Spring Boot vous permet de :
1. Enregistrer un utilisateur.
2. Afficher les détails d'un utilisateur enregistré.

Seuls les résidents français majeurs sont autorisés à créer un compte.

## Fonctionnalités
- Validation des champs obligatoires et optionnels.
- Base de données H2 intégrée pour une installation et une exécution simplifiées.
- Intégration de l'AOP pour journaliser les appels de méthode, les entrées, les sorties et les temps de traitement.

## Prérequis
- Java 17 ou version ultérieure
- Maven 3 ou version ultérieure
- Optionnel : Postman pour tester l'API

## Instructions pour construire le projet à partir des sources

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/MarouaneRom/api-user.git

2. Aller dans le dossier racine du projet :
 ```bash 
   cd french-users-api 
   ```

3. Construire le projet avec Maven:
```bash
   mvn clean install 
   ```

4. Démarrer l'application Spring Boot:
 ```bash
   mvn spring-boot:run
```
# Comment utiliser l'API
## URL de base
### Tous les endpoints sont disponibles à l'adresse suivante :`` http/localhost/8080``

## Endpoints de l'API
1. ### POST /users

### Permet d'enregistrer un nouvel utilisateur.

#### Corps de la requête (exemple) :

``` Json 

{
"name": "Zinedine Zidane",
"birthdate": "2000-01-01",
"country": "France",
"phoneNumber": "+33123456789",
"gender": "Male"
}
```

``` Json 

{
"id": 1,
"name": "Zinedine Zidane",
"birthdate": "2000-01-01",
"country": "France",
"phoneNumber": "+33123456789",
"gender": "Male"
}
```

#### Erreurs possibles :

404 Bad Request if any required field is invalid or missing

#### Exemple de réponse d'erreur :

``` Json 
{
"name": "Name is required."
}
``` 

2. ### GET /users/{id}

Récupère les détails d'un utilisateur spécifique via son ID.

#### Réponse (exemple):

``` Json
{
  "id": 1,
  "name": "Zinedine Zidane",
  "birthdate": "2000-01-01",
  "country": "France",
  "phoneNumber": "+33123456789",
  "gender": "Male"
} 
``` 

#### Erreurs possibles

404 Not Found if the user doesn't exist.

3. ### DELETE /users/{id}

Supprime un utilisateur spécifique via son ID

#### Réponse:

204 No Content on successful deletion

#### Erreurs possibles :

404 Not Found if the users doesn't exist.

## Informations sur la base de données 
Le projet utilise une base de données H2 intégrée pour faciliter les tests. Accédez à la console H2 avec les détails suivants :

### URL : http://localhost:8080/h2-console

### JDBC URL : jdbc:h2:mem:testdb

### Nom d'utilisateur : sa

### Mot de passe: (leave empty)

## Tests de l'API
Une collection Postman est incluse dans le dépôt sous le dossier correspondant. Suivez ces étapes pour tester l'API :

1. Ouvrez Postman.
2. Importez le fichier de collection : French User API.postman_collection.json
3. Exécutez les requêtes pour interagir avec l'API.

## Logs


Toutes les méthodes de l'API sont journalisées grâce à l'AOP :
- Les entrées et sorties des méthodes.
- Le temps de traitement pour chaque méthode

