package fr.esgi.avis;

import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class AvisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvisApplication.class, args);
	}

//	@Bean
//	public UtilisateurGetAvisUseCase utilisateurGetAvisUseCase(UtilisateurGetAvisUseCase.OutputPort outputPort) {
//		return new UtilisateurGetAvisUseCase(outputPort);
//	}
}
