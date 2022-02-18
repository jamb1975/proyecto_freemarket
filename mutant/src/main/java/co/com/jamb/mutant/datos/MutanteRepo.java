package co.com.jamb.mutant.datos;

import co.com.jamb.mutant.domain.Mutante;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

public interface MutanteRepo {

    public Flux<Mutante> findAll();

    public Mono<Tuple3<Long, Long, Double>>  stats();

    public Mono<Mutante> findById(String id);

    public Mono<Mutante> save(Mutante Mutante);

    public Mono<Void> delete(Mutante Mutante);



}
