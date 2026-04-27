package fr.esgi.avis.security;

import fr.esgi.avis.persistance.entity.ModerateurEntity;
import fr.esgi.avis.persistance.repository.ModerateurJpaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final ModerateurJpaRepository moderateurJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.pseudo}")
    private String adminPseudo;

    @Value("${app.admin.password}")
    private String adminPassword;

    public DataInitializer(ModerateurJpaRepository moderateurJpaRepository, PasswordEncoder passwordEncoder) {
        this.moderateurJpaRepository = moderateurJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (moderateurJpaRepository.count() == 0) {
            ModerateurEntity admin = new ModerateurEntity();
            admin.setPseudo(adminPseudo);
            admin.setEmail("admin@avis.fr");
            admin.setMotDePasse(passwordEncoder.encode(adminPassword));
            admin.setNumeroDeTelephone("0000000000");
            moderateurJpaRepository.save(admin);
        }
    }
}
