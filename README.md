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


Étape 3 : Refactor du UserService (DTO, Mapper, Service)

Pour cette étape, le UserService a été restructuré afin d’adopter une architecture plus propre et modulaire. L’objectif était de séparer clairement les responsabilités entre les couches, d’introduire les DTO et de préparer le service pour les futures communications inter-microservices.
•Deux DTO ont été ajoutés :
•UserDTO : utilisé pour les données reçues en entrée (nom, prénom, email).
•UserResponseDTO : renvoyé au client pour encapsuler les données retournées (id, nom, prénom, email).
•Un mapper dédié (UserMapper) a été créé, chargé de convertir proprement :
•un UserDTO → une entité User
•une entité User → un UserResponseDTO
•Une couche service complète a été introduite :
•UserService (interface) pour définir les opérations métier,
•UserServiceImpl pour centraliser la logique et gérer l’accès au repository.
•Le contrôleur a été entièrement refactoré pour ne plus manipuler directement l’entité :
•GET /api/users → liste des utilisateurs (DTO de réponse)
•GET /api/users/{id} → un utilisateur
•POST /api/users → création via UserDTO
•DELETE /api/users/{id} → suppression


