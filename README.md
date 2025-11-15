# Projet : Microservices Spring Boot – Gestion de Tâches Collaboratives

## Étape 1 : UserService – CRUD Utilisateurs
**Statut : ✔️ Réalisée**

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
