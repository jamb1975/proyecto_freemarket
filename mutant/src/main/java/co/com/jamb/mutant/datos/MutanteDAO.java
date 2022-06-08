package co.com.jamb.mutant.datos;

import co.com.jamb.mutant.domain.Mutante;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MutanteDAO extends ReactiveMongoRepository<Mutante, String> {

    @Query("{adn : ?0}")
    Flux<Mutante> findByAdn(String[] adn);
    @Query(value = "{mutant : ?0}", count = true)
    Mono<Long> findAllMutant(boolean mutant );

      

}
