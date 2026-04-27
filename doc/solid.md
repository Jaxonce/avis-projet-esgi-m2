# Principes SOLID

## S — Single Responsibility Principle (Responsabilité unique)

> *Une classe ne doit avoir qu'une seule raison de changer.*

Chaque classe a une responsabilité clairement définie.

**Exemple 1 – `JwtUtil`** ne fait que manipuler les tokens JWT (génération, validation, extraction des claims). Il ne gère pas l'authentification, ni la persistance.

```java
// security/JwtUtil.java
@Component
public class JwtUtil {
    public String generateToken(String username, String role) { ... }
    public String extractUsername(String token) { ... }
    public String extractRole(String token) { ... }
    public Date extractExpiration(String token) { ... }
    public boolean isTokenValid(String token) { ... }
}
```

**Exemple 2 – `ModeratorAddGameUseCase`** ne fait que orchestrer l'ajout d'un jeu (validation + construction du modèle). Il ne s'occupe pas de la persistance ni du mapping HTTP.

```java
// domain/usecase/ModeratorAddGameUseCase.java
@Component
public class ModeratorAddGameUseCase {
    public void apply(CreateJeuRequest request) {
        // valide les IDs, construit le Jeu, délègue à l'OutputPort
    }
}
```

**Exemple 3 – `TokenBlacklistRepositoryImpl`** gère uniquement la blacklist des tokens. Le nettoyage planifié y est aussi logiquement rattaché.

```java
// adapter/TokenBlacklistRepositoryImpl.java
@Component
public class TokenBlacklistRepositoryImpl implements TokenBlacklistRepository {
    public void invalidate(String token, Date expiresAt) { ... }
    public boolean isInvalidated(String token) { ... }

    @Scheduled(fixedRate = 3_600_000)
    public void cleanExpiredTokens() { ... }
}
```

---

## O — Open/Closed Principle (Ouvert/Fermé)

> *Une classe doit être ouverte à l'extension mais fermée à la modification.*

Le pattern `OutputPort` permet d'ajouter de nouvelles implémentations sans toucher au use case.

**Exemple – `ModeratorAddGameUseCase.OutputPort`**

Le use case dépend uniquement de l'interface `OutputPort`. On peut changer la stratégie de persistance (H2 → PostgreSQL, ajout de cache, envoi d'un événement) sans modifier le use case.

```java
// domain/usecase/ModeratorAddGameUseCase.java
public class ModeratorAddGameUseCase {

    public interface OutputPort {
        void save(Jeu jeu);  // contrat ouvert à plusieurs implémentations
    }

    private final OutputPort outputPort; // dépend de l'abstraction, pas du concret

    public void apply(CreateJeuRequest request) {
        // ... logique métier inchangée quelle que soit l'implémentation
        outputPort.save(jeu);
    }
}
```

```java
// adapter/ModeratorAddGameAdapter.java — implémentation actuelle (H2)
@Component
public class ModeratorAddGameAdapter implements ModeratorAddGameUseCase.OutputPort {
    @Override
    public void save(Jeu jeu) { /* persistance JPA */ }
}

// Une future implémentation (ex: audit + persistance) ne modifie pas le use case
// @Component
// public class AuditedGameAdapter implements ModeratorAddGameUseCase.OutputPort {
//     public void save(Jeu jeu) { auditService.log(jeu); jpaAdapter.save(jeu); }
// }
```

---

## L — Liskov Substitution Principle (Substitution de Liskov)

> *Un objet d'une sous-classe doit pouvoir remplacer un objet de la classe parente sans altérer le comportement.*

**Exemple 1 – Hiérarchie `Utilisateur`**

`Joueur` et `Moderateur` héritent de `Utilisateur`. `UserDetailsServiceImpl` traite un `UtilisateurEntity` (classe de base) pour déterminer le rôle, sans avoir besoin de caster.

```java
// security/UserDetailsServiceImpl.java
UtilisateurEntity utilisateur = utilisateurJpaRepository.findByPseudo(username)
        .orElseThrow(...);

// instanceof est utilisé uniquement pour distinguer le rôle,
// pas pour accéder à des comportements spécifiques — substituabilité respectée
String role = utilisateur instanceof ModerateurEntity ? "ROLE_MODERATEUR" : "ROLE_JOUEUR";
```

**Exemple 2 – `LogoutAdapter` substitué dans deux use cases**

`LogoutAdapter` implémente les deux `OutputPort` de logout. Il peut être injecté dans `UtilisateurLogoutUseCase` ou `ModeratorLogoutUseCase` indifféremment, avec le même comportement.

```java
// adapter/LogoutAdapter.java
@Component
public class LogoutAdapter
        implements UtilisateurLogoutUseCase.OutputPort,
                   ModeratorLogoutUseCase.OutputPort {

    @Override
    public void invalidateToken(String token) {
        Date expiresAt = jwtUtil.extractExpiration(token);
        tokenBlacklistRepository.invalidate(token, expiresAt);
    }
}
```

---

## I — Interface Segregation Principle (Ségrégation des interfaces)

> *Un client ne doit pas être forcé de dépendre d'interfaces qu'il n'utilise pas.*

Chaque use case définit sa propre interface `OutputPort` avec uniquement les méthodes dont il a besoin. Aucune interface générique « fourre-tout » n'est imposée.

**Exemple 1 – OutputPorts spécialisés**

```java
// Chaque use case a son propre contrat minimal
public class UtilisateurWriteAvisUseCase {
    public interface OutputPort {
        void writeAvis(Avis avis);  // une seule méthode nécessaire
    }
}

public class ModeratorManageAvisUseCase {
    public interface OutputPort {
        List<Avis> getAllAvis();
        void deleteAvis(Long id);   // deux méthodes, pas plus
    }
}
```

**Exemple 2 – Repositories domaine ciblés**

Au lieu d'un `CrudRepository<T, ID>` générique imposé au domaine, chaque interface de repository n'expose que ce qui est utile :

```java
// domain/repository/EditeurRepository.java
public interface EditeurRepository {
    Optional<Editeur> findById(Long id);  // le domaine n'a besoin que de ça
}

// domain/repository/TokenBlacklistRepository.java
public interface TokenBlacklistRepository {
    void invalidate(String token, Date expiresAt);
    boolean isInvalidated(String token);
    // pas de findAll(), delete(), count()... inutiles ici
}
```

---

## D — Dependency Inversion Principle (Inversion des dépendances)

> *Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau. Les deux doivent dépendre d'abstractions.*

C'est le cœur de l'architecture hexagonale : le domaine (haut niveau) définit les interfaces, les adaptateurs (bas niveau) les implémentent. La dépendance va toujours vers le domaine, jamais l'inverse.

**Exemple 1 – `ModeratorAddGameUseCase` dépend d'abstractions**

```java
// domain/usecase/ModeratorAddGameUseCase.java
@Component
public class ModeratorAddGameUseCase {

    // Dépendances sur des interfaces du domaine, jamais sur des classes concrètes
    private final OutputPort outputPort;           // interface locale
    private final EditeurRepository editeurRepository;   // interface domaine
    private final GenreRepository genreRepository;       // interface domaine
    private final PlateformeRepository plateformeRepository; // interface domaine

    // Spring injecte EditeurRepositoryImpl, GenreRepositoryImpl... à l'exécution
}
```

**Exemple 2 – `JwtAuthenticationFilter` dépend de `TokenBlacklistRepository`**

```java
// security/JwtAuthenticationFilter.java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final TokenBlacklistRepository tokenBlacklistRepository; // interface, pas l'impl

    protected void doFilterInternal(...) {
        if (jwtUtil.isTokenValid(token) && !tokenBlacklistRepository.isInvalidated(token)) {
            // authentification
        }
    }
}
```

Le filtre ne sait pas que la blacklist est stockée en H2. Si demain on la stocke en Redis, seul `TokenBlacklistRepositoryImpl` change.

**Schéma des dépendances**

```
[ModerateurController]
        │ dépend de
        ▼
[ModeratorAddGameUseCase]         ← module de haut niveau
        │ dépend de (interfaces)
        ├──► [OutputPort]  ◄──── implémenté par [ModeratorAddGameAdapter]
        ├──► [EditeurRepository] ◄── implémenté par [EditeurRepositoryImpl]
        ├──► [GenreRepository] ◄──── implémenté par [GenreRepositoryImpl]
        └──► [PlateformeRepository] ◄ implémenté par [PlateformeRepositoryImpl]
                                              │ dépend de
                                              ▼
                                    [JPA Repositories]  ← module de bas niveau
```

Les flèches de dépendance vont toujours **vers le domaine**, jamais dans l'autre sens.