package co.com.jamb.mutant.servicio;

import co.com.jamb.mutant.domain.Mutante;
import co.com.jamb.mutant.domain.Stats;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MutanteService {
     public Flux<Mutante> findAll();
     public Mono<Stats> stats();
     public Mono<Mutante> mutant(Mutante mutante);
}
