# Avis de Jeux

Application full-stack de gestion d'avis sur des jeux vidéo.

## Prérequis

- Java 25+
- Maven (ou utiliser le wrapper `./mvnw` inclus)
- Node.js 18+

## Backend

Le backend est une API REST Spring Boot qui tourne sur `http://localhost:8081`.

La base de données est **H2** en mode fichier — elle est créée automatiquement au premier démarrage, aucun fichier à importer. Il faut simplement que le dossier `backend/data/` existe :

```bash
mkdir -p backend/data
```

Puis lancer l'application depuis le dossier `backend/` :

```bash
cd backend
./mvnw spring-boot:run
```

**Accès utiles une fois démarré :**
- Swagger UI : http://localhost:8081/swagger-ui.html
- Console H2 : http://localhost:8081/h2-console (JDBC URL : `jdbc:h2:file:./data/avisdb`, user : `sa`, pas de mot de passe)

Un compte modérateur par défaut est créé au démarrage : `admin` / `admin123`.

## Frontend

Le frontend est une application React + Vite qui tourne sur `http://localhost:5173`.

```bash
cd frontend
npm install
npm run dev
```

## Comptes

| Rôle       | Création |
|------------|----------|
| Joueur     | Via le formulaire d'inscription sur la page de connexion |
| Modérateur | Via le formulaire d'inscription (onglet Modérateur), ou compte par défaut `admin` / `admin123` |

## Documentation technique

Vous trouverez la documentation technique dans le dossier suivant :

`backend/doc`
