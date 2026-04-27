# Choix architecturaux

## Architecture Hexagonale (Ports & Adaptateurs)

L'application est construite sur le pattern **Hexagonal Architecture** (aussi appelé *Ports & Adapters* ou *Architecture en oignon*). L'objectif est d'isoler la logique métier (le domaine) de toute dépendance technique (framework, base de données, HTTP).

### Principe général

```
[Client HTTP]
     │
     ▼
┌─────────────────────────────────────────────────────┐
│  presenter/          (Contrôleurs REST)             │
│  Entrée : appels HTTP → appels aux Use Cases        │
├─────────────────────────────────────────────────────┤
│  domain/usecase/     (Logique métier)               │
│  Définit des OutputPort (interfaces)                │
├─────────────────────────────────────────────────────┤
│  adapter/            (Implémentations des ports)    │
│  Implémente les OutputPort, appelle la persistance  │
├─────────────────────────────────────────────────────┤
│  persistance/        (JPA / H2)                     │
│  Entités JPA + Spring Data Repositories             │
└─────────────────────────────────────────────────────┘
```

### Responsabilités par couche

| Couche | Package | Rôle |
|--------|---------|------|
| **Présentation** | `presenter/` | Contrôleurs REST. Reçoit les requêtes HTTP, appelle les use cases, retourne des DTOs. |
| **Domaine – Use Cases** | `domain/usecase/` | Logique métier pure. Chaque use case définit une interface `OutputPort` que son adaptateur doit implémenter. |
| **Domaine – Modèles** | `domain/model/` | Entités métier sans annotation technique (pas de JPA, pas de Jackson). |
| **Domaine – Repositories** | `domain/repository/` | Interfaces de repositories définies côté domaine (pas d'implémentation). |
| **Domaine – Mappers** | `domain/mapper/` | Interfaces MapStruct pour convertir Entity ↔ Model ↔ DTO. |
| **Domaine – DTOs** | `domain/dto/` | Records Java utilisés pour les entrées/sorties de l'API. |
| **Adaptateurs** | `adapter/` | Implémentent les `OutputPort` des use cases et les interfaces de repository du domaine. Pont entre domaine et persistance. |
| **Persistance** | `persistance/` | Entités JPA avec annotations `@Entity`, `@ManyToOne`, etc. Spring Data JPA repositories. |
| **Sécurité** | `security/` | JWT, filtres Spring Security, configuration des règles d'accès. |

---

## Flux d'exécution : exemple "Ajouter un jeu"

```
POST /moderateur/jeu
        │
        ▼
JwtAuthenticationFilter         ← vérifie le token JWT et la blacklist
        │
        ▼
ModerateurController.addJeu()   ← extrait le body de la requête
        │
        ▼
ModeratorAddGameUseCase.apply() ← valide les IDs (éditeur, genre, plateformes)
        │                          construit l'objet Jeu (domaine pur)
        ▼
OutputPort.save(jeu)            ← interface définie dans le use case
        │
        ▼
ModeratorAddGameAdapter.save()  ← convertit Jeu → JeuEntity via MapStruct,
        │                          résout les FK avec getReferenceById()
        ▼
JeuJpaRepository.save()         ← Spring Data JPA → H2
```

---

## Choix techniques

### Java 25 + Spring Boot 4.0.5
Version récente pour bénéficier des Records, des pattern matching et des API modernes.

### H2 (base de données fichier)
Base embarquée persistée sur fichier (`./data/avisdb`). Permet de travailler sans infrastructure externe. Facilement remplaçable par PostgreSQL ou MySQL grâce à l'architecture hexagonale (seule la couche `persistance/` changerait).

### MapStruct 1.6.3
Génération de code de mapping à la compilation. Zéro reflection à l'exécution, performances optimales, erreurs détectées à la compilation.

### JWT (JSON Web Token)
Authentification stateless. Les tokens sont signés avec HMAC-SHA256. La déconnexion est gérée via une table de blacklist (`token_blacklist`) et un nettoyage planifié horaire des tokens expirés.

### Spring Security
Filtrage des requêtes par rôle (`ROLE_JOUEUR`, `ROLE_MODERATEUR`). Le filtre `JwtAuthenticationFilter` est injecté avant `UsernamePasswordAuthenticationFilter`.

### Lombok
Réduction du boilerplate (getters, setters, constructeurs, equals/hashCode) via annotations traitées à la compilation.