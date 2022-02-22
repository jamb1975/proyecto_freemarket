package co.com.jamb.mutant.datos;

import co.com.jamb.mutant.domain.Mutante;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;

public interface MutanteRepo {

    public Flux<Mutante> findAll();

    public Mono<Mutante> save(Mutante Mutante);

    public Mono<Void> delete(Mutante Mutante);

    public Flux<Mutante> findByAdn(String[] adn);

    public Mono<Long> findAllMutant(boolean mutant);

}
