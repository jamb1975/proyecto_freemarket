package co.com.jamb.mutant.datos;

import co.com.jamb.mutant.domain.Mutante;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MutanteDAO extends ReactiveMongoRepository<Mutante, String> {

}
