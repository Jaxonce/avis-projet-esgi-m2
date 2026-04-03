package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.ModerateurMapper;
import fr.esgi.avis.domain.model.Moderateur;
import fr.esgi.avis.domain.repository.ModerateurRepository;
import fr.esgi.avis.persistance.repository.ModerateurJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ModerateurRepositoryImpl implements ModerateurRepository {

    private final ModerateurJpaRepository moderateurJpaRepository;

    public ModerateurRepositoryImpl(ModerateurJpaRepository moderateurJpaRepository) {
        this.moderateurJpaRepository = moderateurJpaRepository;
    }

    @Override
    public void save(Moderateur moderateur) {
        moderateurJpaRepository.save(ModerateurMapper.INSTANCE.moderateurToModerateurEntity(moderateur));
    }
}
