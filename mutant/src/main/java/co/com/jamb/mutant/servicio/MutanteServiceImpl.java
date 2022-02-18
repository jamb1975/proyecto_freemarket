package co.com.jamb.mutant.servicio;

import co.com.jamb.mutant.datos.MutanteRepo;
import co.com.jamb.mutant.domain.Mutante;
import co.com.jamb.mutant.domain.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MutanteServiceImpl implements MutanteService {

    @Autowired
    private MutanteRepo mutanteRepo;
    @Override
    public Flux<Mutante> findAll() {
        return mutanteRepo.findAll();
    }

    @Override
    public Mono<Stats> stats() {
        return mutanteRepo.stats().map(s->new Stats(s.getT1(), s.getT2(), s.getT3()));
    }

    @Override
    public Mono<Mutante> mutant(Mutante mutante) {
        return mutanteRepo.save(mutante);
    }
}
