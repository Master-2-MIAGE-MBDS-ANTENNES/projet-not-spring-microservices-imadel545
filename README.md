# Projet : Microservices Spring Boot – Gestion de Tâches Collaboratives

## Étape 1 : UserService – CRUD Utilisateurs


Fonctionnalités développées :
- Création du microservice `UserService`
- Mise en place de l'entité `User` (nom, prénom, email)
- Repository JPA : `UserRepository`
- Contrôleur REST : `UserController`
- Endpoints disponibles :
  - `GET /api/users`
  - `POST /api/users`
  - `GET /api/users/{id}`
  - `DELETE /api/users/{id}`
- Configuration H2 + JPA (création du schéma en mémoire)
- Vérification de fonctionnement avec Spring Boot


## Étape 2 : TaskService – CRUD Tâches 

Fonctionnalités :
- Microservice `taskservice`
- Entité `Task` (titre, description, status)
- Enum `TaskStatus` (OUVERTE, EN_COURS, TERMINEE)
- Repository : `TaskRepository`
- Controller : `TaskController`
- Endpoints :
  - GET /api/tasks
  - POST /api/tasks
  - GET /api/tasks/{id}
  - DELETE /api/tasks/{id}
- Base H2 en mémoire (`taskdb`) sur port 8081.
