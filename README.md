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

## Étape 5 : Intégration de Feign (TaskService → UserService)

Dans cette étape, le `TaskService` a été connecté au `UserService` à l’aide de Spring Cloud OpenFeign afin de permettre la validation des utilisateurs associés à une tâche.

- Ajout de la dépendance `spring-cloud-starter-openfeign` dans le `pom.xml` du `taskservice`.
- Activation de Feign via l’annotation `@EnableFeignClients` dans `TaskserviceApplication`.
- Création d’un client Feign `UserClient` pointant vers le `UserService` (appel sur `/api/users/{id}`).
- Évolution du modèle côté `TaskService` pour gérer une liste d’utilisateurs associés à une tâche (liste d’identifiants dans le DTO).
- Adaptation de la couche service (`TaskService` / `TaskServiceImpl`) pour :
  - Vérifier l’existence des utilisateurs via le client Feign avant création de la tâche.
  - Exposer les informations via les DTO (`TaskDTO` / `TaskResponseDTO`).
- Tests réalisés :
  - Création d’un utilisateur via `UserService` (`POST /api/users` sur le port 8080).
  - Création d’une tâche via `TaskService` (`POST /api/tasks` sur le port 8081) en fournissant les `userIds` et validation de l’appel Feign.

## Étape 6 : Mise en Place d’une Gateway Dynamique

Création du microservice gatewayservice (Spring Cloud Gateway) sur le port 8080

Configuration des routes dynamiques via Eureka

Activation du routage dynamique basé sur les noms des services

Accès aux services via la Gateway

## Étape 7 : Mise en place d’une API Gateway dynamique
- Création du projet `apigateway` (Spring Cloud Gateway).
- Configuration de l’enregistrement Eureka.
- Routes dynamiques :
  - `/userservice/**` → `USERSERVICE`
  - `/taskservice/**` → `TASKSERVICE`
- Tests réalisés :
  - `curl http://localhost:8082/userservice/api/users`
  - `curl http://localhost:8082/taskservice/api/tasks`

## Étape 8 : Ajout de la Résilience avec Resilience4J

Cette étape a introduit des mécanismes de tolérance aux pannes afin de sécuriser les appels réseau entre le TaskService et le UserService. L’objectif était d’éviter qu’une panne du UserService bloque le fonctionnement global de l’application.

Fonctionnalités intégrées :
•	Ajout des dépendances Resilience4J (circuitbreaker, retry, spring-boot3) dans le pom.xml du taskservice.
•	Activation d’AOP et de Feign CircuitBreaker pour permettre l’utilisation des annotations.
•	Configuration complète dans application.properties :
•	CircuitBreaker (userService)
•	Retry (userService)
•	Exposition Actuator (/actuator/circuitbreakers)