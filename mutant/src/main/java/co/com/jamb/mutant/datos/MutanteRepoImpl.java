package co.com.jamb.mutant.datos;

import co.com.jamb.mutant.domain.Mutante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.List;

@Repository
public class MutanteRepoImpl implements MutanteRepo{

    @Autowired
    private MutanteDAO mutanteDAO;

    @Override
    public Flux<Mutante> findAll() {
        return mutanteDAO.findAll();
    }


    @Override
    public Mono<Mutante> save(Mutante mutante) {
        return mutanteDAO.save(mutante);
    }

    @Override
    public Mono<Void> delete(Mutante mutante) {
        return mutanteDAO.delete(mutante);
    }

    @Override
    public Flux<Mutante> findByAdn(String[] adn) {
        return  mutanteDAO.findByAdn(adn);
    }

    @Override
    public Mono<Long> findAllMutant(boolean mutant) {
        return mutanteDAO.findAllMutant(mutant);
    }

}
