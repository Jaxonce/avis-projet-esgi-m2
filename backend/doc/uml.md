# Modélisation UML

## 1. Diagramme de classes (modèle domaine)

```mermaid
classDiagram
    direction TB

    class Utilisateur {
        <<abstract>>
        #Long id
        #String pseudo
        #String email
        #String motDePasse
    }

    class Joueur {
        -LocalDate dateDeNaissance
        -Avatar avatar
        -List~Avis~ avis
    }

    class Moderateur {
        -String numeroDeTelephone
    }

    class Jeu {
        -Long id
        -String nom
        -LocalDate dateDeSortie
        -String description
        -Double prix
        -String image
        -boolean possedeImage
        -Editeur editeur
        -Genre genre
        -Classification classification
        -List~Plateforme~ plateformes
    }

    class Avis {
        -Long id
        -String description
        -Double note
        -LocalDateTime dateDenvoi
        -Jeu jeu
        -Joueur joueur
        -Moderateur moderateur
    }

    class Editeur {
        -Long id
        -String nom
    }

    class Genre {
        -Long id
        -String nom
    }

    class Plateforme {
        -Long id
        -String nom
        -LocalDate dateDeSortie
    }

    class Classification {
        -Long id
        -String nom
        -String couleurRGB
    }

    class Avatar {
        -Long id
    }

    Utilisateur <|-- Joueur
    Utilisateur <|-- Moderateur

    Joueur "1" --> "0..*" Avis : écrit
    Avis "0..*" --> "1" Jeu : concerne
    Avis "0..*" --> "0..1" Moderateur : modéré par

    Jeu "0..*" --> "1" Editeur : publié par
    Jeu "0..*" --> "1" Genre : appartient à
    Jeu "0..*" --> "0..1" Classification : classifié par
    Jeu "0..*" --> "1..*" Plateforme : disponible sur

    Joueur "0..1" --> "0..1" Avatar : possède
```

---

## 2. Diagramme de classes (couches techniques)

```mermaid
classDiagram
    direction LR

    class ModerateurController {
        +addJeu(CreateJeuRequest)
        +login(LoginRequest) LoginResponse
        +logout(HttpServletRequest)
        +getAllAvis() List~AvisDto~
        +deleteAvis(Long)
    }

    class ModeratorAddGameUseCase {
        <<Component>>
        +apply(CreateJeuRequest)
    }

    class OutputPort {
        <<interface>>
        +save(Jeu)
    }

    class ModeratorAddGameAdapter {
        <<Component>>
        +save(Jeu)
    }

    class JeuJpaRepository {
        <<interface>>
        +save(JeuEntity) JeuEntity
    }

    class EditeurRepository {
        <<interface>>
        +findById(Long) Optional~Editeur~
    }

    class GenreRepository {
        <<interface>>
        +findById(Long) Optional~Genre~
    }

    class PlateformeRepository {
        <<interface>>
        +findById(Long) Optional~Plateforme~
    }

    ModerateurController --> ModeratorAddGameUseCase : appelle
    ModeratorAddGameUseCase --> OutputPort : utilise
    ModeratorAddGameUseCase --> EditeurRepository : vérifie existence
    ModeratorAddGameUseCase --> GenreRepository : vérifie existence
    ModeratorAddGameUseCase --> PlateformeRepository : vérifie existence
    ModeratorAddGameAdapter ..|> OutputPort : implémente
    ModeratorAddGameAdapter --> JeuJpaRepository : persiste
```

---

## 3. Diagramme état-transition : cycle de vie d'un Jeu

Un jeu possède deux états observables selon la présence ou non d'avis.

```mermaid
---
config:
  theme: dark
  look: classic
---
stateDiagram-v2
    [*] --> Inexistant

    Inexistant --> Disponible : POST /moderateur/jeu

    Disponible --> Commenté : POST /utilisateur/avis

    Commenté --> PartiellementModéré : DELETE /moderateur/avis/{id}

    PartiellementModéré --> Commenté : POST /utilisateur/avis
```

**Explication des états :**

| État | Description |
|------|-------------|
| **Disponible** | Le jeu existe en base, visible via `GET /utilisateur/jeu`, aucun avis associé |
| **Commenté** | Le jeu possède au moins un avis, visible via `GET /utilisateur/avis` |

**Transitions :**

| De | Vers | Déclencheur |
|----|------|-------------|
| *(initial)* | Disponible | `POST /moderateur/jeu` |
| Disponible | Commenté | `POST /utilisateur/avis` |
| Commenté | Commenté | `POST /utilisateur/avis` ou `DELETE /moderateur/avis/{id}` (s'il reste des avis) |
| Commenté | Disponible | `DELETE /moderateur/avis/{id}` (suppression du dernier avis) |

---

## 4. Diagramme de séquence : Use Case "Ajouter un jeu"

```mermaid
sequenceDiagram
    actor Modérateur
    participant Filter as JwtAuthenticationFilter
    participant Blacklist as TokenBlacklistRepository
    participant Controller as ModerateurController
    participant UseCase as ModeratorAddGameUseCase
    participant EditeurRepo as EditeurRepository
    participant GenreRepo as GenreRepository
    participant PlateformeRepo as PlateformeRepository
    participant Adapter as ModeratorAddGameAdapter
    participant JeuJpa as JeuJpaRepository
    participant DB as Base de données (H2)

    Modérateur->>Filter: POST /moderateur/jeu\nAuthorization: Bearer <token>
    Filter->>Blacklist: isInvalidated(token)
    Blacklist-->>Filter: false
    Filter->>Filter: setAuthentication(ROLE_MODERATEUR)
    Filter->>Controller: requête transmise

    Controller->>UseCase: apply(CreateJeuRequest)

    UseCase->>EditeurRepo: findById(editeurId)
    EditeurRepo-->>UseCase: Optional<Editeur>
    alt Éditeur introuvable
        UseCase-->>Controller: IllegalArgumentException
        Controller-->>Modérateur: 500 (éditeur non trouvé)
    end

    UseCase->>GenreRepo: findById(genreId)
    GenreRepo-->>UseCase: Optional<Genre>
    alt Genre introuvable
        UseCase-->>Controller: IllegalArgumentException
        Controller-->>Modérateur: 500 (genre non trouvé)
    end

    loop Pour chaque plateformeId
        UseCase->>PlateformeRepo: findById(plateformeId)
        PlateformeRepo-->>UseCase: Optional<Plateforme>
        alt Plateforme introuvable
            UseCase-->>Controller: IllegalArgumentException
            Controller-->>Modérateur: 500 (plateforme non trouvée)
        end
    end

    UseCase->>UseCase: construit Jeu (domaine pur)
    UseCase->>Adapter: save(jeu)

    Adapter->>Adapter: JeuMapper.jeuToJeuEntity(jeu)
    Adapter->>Adapter: editeurJpaRepository.getReferenceById(editeurId)
    Adapter->>Adapter: genreJpaRepository.getReferenceById(genreId)
    Adapter->>Adapter: plateformeJpaRepository.getReferenceById(plateformeId)
    Adapter->>JeuJpa: save(jeuEntity)
    JeuJpa->>DB: INSERT INTO jeu_entity ...
    DB-->>JeuJpa: OK
    JeuJpa-->>Adapter: JeuEntity sauvegardée
    Adapter-->>UseCase: (void)
    UseCase-->>Controller: (void)
    Controller-->>Modérateur: 201 Created
```
