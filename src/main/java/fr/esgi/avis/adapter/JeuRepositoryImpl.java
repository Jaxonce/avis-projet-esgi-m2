package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.JeuMapper;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.repository.JeuRepository;
import fr.esgi.avis.persistance.repository.JeuJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JeuRepositoryImpl implements JeuRepository {

    private final JeuJpaRepository jeuJpaRepository;

    public JeuRepositoryImpl(JeuJpaRepository jeuJpaRepository) {
        this.jeuJpaRepository = jeuJpaRepository;
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        return jeuJpaRepository.findById(id).map(JeuMapper.INSTANCE::jeuEntityToJeu);
    }

    @Override
    public void save(Jeu jeu) {
        jeuJpaRepository.save(JeuMapper.INSTANCE.jeuToJeuEntity(jeu));
    }
}
