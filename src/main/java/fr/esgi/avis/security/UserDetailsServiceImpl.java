package fr.esgi.avis.security;

import fr.esgi.avis.persistance.entity.ModerateurEntity;
import fr.esgi.avis.persistance.entity.UtilisateurEntity;
import fr.esgi.avis.persistance.repository.UtilisateurJpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurJpaRepository utilisateurJpaRepository;

    public UserDetailsServiceImpl(UtilisateurJpaRepository utilisateurJpaRepository) {
        this.utilisateurJpaRepository = utilisateurJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity utilisateur = utilisateurJpaRepository.findByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        String role = utilisateur instanceof ModerateurEntity ? "ROLE_MODERATEUR" : "ROLE_JOUEUR";

        return new User(utilisateur.getPseudo(), utilisateur.getMotDePasse(),
                List.of(new SimpleGrantedAuthority(role)));
    }
}
