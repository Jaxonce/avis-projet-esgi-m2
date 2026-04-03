package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.JoueurMapper;
import fr.esgi.avis.domain.model.Joueur;
import fr.esgi.avis.domain.repository.JoueurRepository;
import fr.esgi.avis.persistance.repository.JoueurJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JoueurRepositoryImpl implements JoueurRepository {

    private final JoueurJpaRepository joueurJpaRepository;

    public JoueurRepositoryImpl(JoueurJpaRepository joueurJpaRepository) {
        this.joueurJpaRepository = joueurJpaRepository;
    }

    @Override
    public Optional<Joueur> findById(Long id) {
        return joueurJpaRepository.findById(id).map(JoueurMapper.INSTANCE::joueurEntityToJoueur);
    }

    @Override
    public void save(Joueur joueur) {
        joueurJpaRepository.save(JoueurMapper.INSTANCE.joueurToJoueurEntity(joueur));
    }
}
